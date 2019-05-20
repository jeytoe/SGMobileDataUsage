package com.tuanna.sgmobiledatausage.database

import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity
import com.tuanna.sgmobiledatausage.network.MobileUsageAPI
import io.reactivex.Observable
import javax.inject.Inject

class RecordProvider @Inject constructor(
    private val recordRepository: RecordRepository,
    private val mobileUsageService: MobileUsageAPI
) {

    fun getRecordsFromAPI(): Observable<ResponseWrapper> {
        return mobileUsageService.getMobileDataUsageData
            .map {
                recordRepository.updateRecords(it.result?.records?.map { record ->
                    QuarterRecordEntity(record.volume!!, record.quarter!!)
                } ?: emptyList())
                ResponseWrapper(it)
            }
    }

    fun getRecordsFromDatabase(): Observable<ResponseWrapper> {
        val entities = recordRepository.getAllRecords()
        return Observable.just(ResponseWrapper(entities))
    }
}
