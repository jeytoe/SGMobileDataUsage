package com.tuanna.sgmobiledatausage.main

import android.support.v7.app.AppCompatActivity
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModel

interface Contract {

    interface View {
        fun showLoadingSpinner()
        fun hideLoadingSpinner()
        fun showDataList(viewModels: List<MobileDataUsageViewModel>)
        fun displayPopup(message: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun onViewResumed(hasNetworkConnection: Boolean)
        fun onViewStopped()
    }
}