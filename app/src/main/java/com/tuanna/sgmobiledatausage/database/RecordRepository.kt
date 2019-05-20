package com.tuanna.sgmobiledatausage.database

import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity
import javax.inject.Inject

class RecordRepository @Inject constructor(var daoService: DaoService) {

    fun getAllRecords(): List<QuarterRecordEntity> {
        return daoService.getAllRecords()
    }

    fun updateRecords(records: List<QuarterRecordEntity>) {
        daoService.deleteAllRecords()
        daoService.insertRecords(records)
    }
}