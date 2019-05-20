package com.tuanna.sgmobiledatausage.database

import com.nhaarman.mockito_kotlin.verify
import com.tuanna.sgmobiledatausage.network.MobileDataUsageResponse
import com.tuanna.sgmobiledatausage.network.MobileUsageAPI
import com.tuanna.sgmobiledatausage.network.QuarterRecord
import com.tuanna.sgmobiledatausage.network.Result
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyList
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecordProviderTest {

    @InjectMocks
    lateinit var subject: RecordProvider

    @Mock
    lateinit var service: MobileUsageAPI

    @Mock
    lateinit var repository: RecordRepository

    @Test
    fun getRecordsFromAPI() {
        val records = listOf(QuarterRecord(0.11, "2019-Q2"))
        `when`(service.getMobileDataUsageData).thenReturn(
            Observable.just(MobileDataUsageResponse(true, Result(records))))

        val testObserver = subject.getRecordsFromAPI().test()

        verify(service).getMobileDataUsageData
        verify(repository).updateRecords(anyList())
        testObserver.assertValue { wrapper -> wrapper.records.size == 1 }
    }

    @Test
    fun getRecordsFromDatabase() {
        subject.getRecordsFromDatabase()

        verify(repository).getAllRecords()
    }
}