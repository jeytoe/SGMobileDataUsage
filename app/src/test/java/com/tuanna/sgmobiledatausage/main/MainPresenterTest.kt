package com.tuanna.sgmobiledatausage.main

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.tuanna.sgmobiledatausage.database.RecordProvider
import com.tuanna.sgmobiledatausage.database.ResponseWrapper
import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModelFactory
import com.tuanna.sgmobiledatausage.network.MobileDataUsageResponse
import com.tuanna.sgmobiledatausage.network.MobileUsageAPI
import com.tuanna.sgmobiledatausage.network.QuarterRecord
import com.tuanna.sgmobiledatausage.network.Result
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyList
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @InjectMocks
    lateinit var subject: MainPresenter

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    @Mock
    lateinit var viewModelFactory: MobileDataUsageViewModelFactory

    @Mock
    lateinit var view: Contract.View

    @Mock
    lateinit var recordProvider: RecordProvider

    @Before
    fun setUp() {
        subject.setView(view)
    }

    @Test
    fun onViewResumed_givenNetworkIsAvailable_verifyServiceCalled() {
        `when`(recordProvider.getRecordsFromAPI())
            .thenReturn(Observable.just(ResponseWrapper(emptyList())))
        subject.onViewResumed(true)
        verify(recordProvider).getRecordsFromAPI()
    }

    @Test
    fun onViewResumed_givenNetworkNotAvailable_verifyRepositoryCalled() {
        `when`(recordProvider.getRecordsFromDatabase())
            .thenReturn(Observable.just(ResponseWrapper(
                listOf(QuarterRecordEntity(0.11, "2019-Q2")))))
        subject.onViewResumed(false)
        verify(recordProvider).getRecordsFromDatabase()
    }

    @Test
    fun onViewResumed_verifySpinnerShownAndHidden() {
        `when`(recordProvider.getRecordsFromAPI())
            .thenReturn(Observable.just(ResponseWrapper(emptyList())))
        subject.onViewResumed(true)
        verify(view).showLoadingSpinner()
        verify(view).hideLoadingSpinner()
    }

    @Test
    fun onViewResumed_onSuccessResponse_verifyFactoryAndViewMethodsCalled() {
        val records = emptyList<QuarterRecord>()
        `when`(recordProvider.getRecordsFromAPI())
            .thenReturn(Observable.just(ResponseWrapper(emptyList())))
        subject.onViewResumed(true)
        verify(viewModelFactory).getViewModels(records)
        verify(view).showDataList(anyList())
    }

    @Test
    fun onViewResumed_onFailureResponse_verifyFactoryAndViewMethodsDidNotGetCalled() {
        `when`(recordProvider.getRecordsFromAPI())
            .thenReturn(Observable.error(IllegalArgumentException()))
        subject.onViewResumed(true)
        verifyZeroInteractions(viewModelFactory)
        verify(view, times(0)).showDataList(anyList())
        verify(view).displayPopup("Error! Please try again!")
    }

    @Test
    fun onViewStopped() {
        subject.onViewStopped()
        verify(compositeDisposable).clear()
    }
}