package com.tuanna.sgmobiledatausage.main

import android.support.v7.app.AppCompatActivity

interface Contract {

    interface View {
        fun showLoadingSpinner()
        fun hideLoadingSpinner()
        fun displayPopup(message: String)
    }

    interface Presenter {
        fun setView(view: View)
        fun onViewResumed()
        fun onViewStopped()
    }
}