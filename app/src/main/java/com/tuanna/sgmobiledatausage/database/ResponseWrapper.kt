package com.tuanna.sgmobiledatausage.database

import com.tuanna.sgmobiledatausage.database.entity.QuarterRecordEntity
import com.tuanna.sgmobiledatausage.network.MobileDataUsageResponse
import com.tuanna.sgmobiledatausage.network.QuarterRecord

class ResponseWrapper {

    var records: List<QuarterRecord> = emptyList()

    constructor(response: MobileDataUsageResponse) {
        this.records = response.result?.records ?: emptyList()
    }

    constructor(records: List<QuarterRecordEntity>) {
        this.records = records.map {
            QuarterRecord(it.volume, it.quarter)
        }
    }
}