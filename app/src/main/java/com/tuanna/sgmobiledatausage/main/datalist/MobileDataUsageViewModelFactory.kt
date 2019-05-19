package com.tuanna.sgmobiledatausage.main.datalist

import com.tuanna.sgmobiledatausage.network.QuarterRecord
import javax.inject.Inject

class MobileDataUsageViewModelFactory @Inject constructor() {

    fun getViewModels(records: List<QuarterRecord>?): List<MobileDataUsageViewModel> {
        if (records == null || records.isEmpty()) {
            return emptyList()
        }

        return records.groupBy { it.year }
            .map {
               MobileDataUsageViewModel(it.key!!, it.value.sumByDouble { record -> record.volume!! })
            }
    }
}