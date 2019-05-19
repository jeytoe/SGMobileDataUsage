package com.tuanna.sgmobiledatausage.main

import com.nhaarman.mockito_kotlin.verify
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
    lateinit var mobileDataUsageService: MobileUsageAPI

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    @Mock
    lateinit var viewModelFactory: MobileDataUsageViewModelFactory

    @Mock
    lateinit var view: Contract.View

    @Before
    fun setUp() {
        subject.setView(view)
    }

    @Test
    fun onViewResumed_verifyServiceCalled() {
        `when`(mobileDataUsageService.getMobileDataUsageData)
            .thenReturn(Observable.just(MobileDataUsageResponse(true, null)))
        subject.onViewResumed()
        verify(mobileDataUsageService).getMobileDataUsageData
    }

    @Test
    fun onViewResumed_verifySpinnerShownAndHidden() {
        `when`(mobileDataUsageService.getMobileDataUsageData)
            .thenReturn(Observable.just(MobileDataUsageResponse(true, null)))
        subject.onViewResumed()
        verify(view).showLoadingSpinner()
        verify(view).hideLoadingSpinner()
    }

    @Test
    fun onViewResumed_onSuccessResponse_verifyFactoryAndViewMethodsCalled() {
        val records = emptyList<QuarterRecord>()
        `when`(mobileDataUsageService.getMobileDataUsageData)
            .thenReturn(Observable.just(MobileDataUsageResponse(true, Result(records))))
        subject.onViewResumed()
        verify(viewModelFactory).getViewModels(records)
        verify(view).showDataList(ArgumentMatchers.anyList())
    }

    @Test
    fun onViewStopped() {
        subject.onViewStopped()
        verify(compositeDisposable).clear()
    }
}