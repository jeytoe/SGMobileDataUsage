package com.tuanna.sgmobiledatausage.main.datalist

class MobileDataUsageViewModel(
    var year: Int,
    var total: Double,
    var shouldShowIcon: Boolean = false) {

    var message:String = ""
    get() {
        return "Data usage of $year is ${String.format("%.5f", total)} PB"
    }
}