package com.tuanna.sgmobiledatausage.main

import android.os.Bundle
import com.tuanna.sgmobiledatausage.R
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : BaseActivity(), Contract.View {

    @Inject
    lateinit var presenter: Contract.Presenter

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getToolbarTitle(): Int {
        return R.string.app_name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewStopped()
    }

    override fun showDataList(viewModels: List<MobileDataUsageViewModel>) {
    }

    override fun displayPopup(message: String) {
    }

    override fun injectDependency() {
        AndroidInjection.inject(this)
    }

    override fun showLoadingSpinner() {
        showSpinner()
    }

    override fun hideLoadingSpinner() {
        hideSpinner()
    }
}
