package com.tuanna.sgmobiledatausage.network

import com.google.gson.annotations.SerializedName

data class MobileDataUsageResponse(var records: List<QuarterRecord>?)

data class QuarterRecord(
    @SerializedName("volume_of_mobile_data")
    var volume: Double?,
    var quarter: String?
) {

    var quarterIndex: Int? = null
        get() {
            return quarter?.split("-")?.get(1)?.toInt()
        }

    var year: Int? = null
        get() {
            return quarter?.split("-")?.get(0)?.toInt()
        }
}