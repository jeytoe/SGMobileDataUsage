package com.tuanna.sgmobiledatausage.main

import android.os.Bundle
import android.support.v7.app.AlertDialog.Builder
import android.support.v7.widget.LinearLayoutManager
import com.tuanna.sgmobiledatausage.R
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageAdapter
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModel
import com.tuanna.sgmobiledatausage.network.NetworkConnectivityUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), Contract.View {

    @Inject
    lateinit var presenter: Contract.Presenter

    @Inject
    lateinit var adapter: MobileDataUsageAdapter

    @Inject
    lateinit var networkConnectivityUtil: NetworkConnectivityUtil

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
        presenter.onViewResumed(networkConnectivityUtil.isNetworkAvailable(this))
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewStopped()
    }

    override fun showDataList(viewModels: List<MobileDataUsageViewModel>) {
        adapter.onIconClickedListener = this::displayPopup
        adapter.viewModels = viewModels
        dataListView.adapter = adapter
        dataListView.layoutManager = LinearLayoutManager(this)
    }

    override fun displayPopup(message: String) {
        val builder = Builder(this)
        builder
            .setMessage(message)
            .setPositiveButton("OK", null)
            .setCancelable(false)
            .show()
    }

    override fun injectDependency() {
        AndroidInjection.inject(this)
    }

    /**
     * Simulate showing loading spinner.
     * Can use fragment dialog, depends on requirements
     */
    override fun showLoadingSpinner() {
        showSpinner()
    }

    /**
     * Simulate hiding loading spinner
     */
    override fun hideLoadingSpinner() {
        hideSpinner()
    }
}
