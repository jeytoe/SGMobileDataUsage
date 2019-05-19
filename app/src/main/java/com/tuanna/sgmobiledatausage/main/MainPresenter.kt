package com.tuanna.sgmobiledatausage.main

import com.tuanna.sgmobiledatausage.main.Contract.View
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModelFactory
import com.tuanna.sgmobiledatausage.network.MobileDataUsageResponse
import com.tuanna.sgmobiledatausage.network.MobileUsageAPI
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private var mobileDataUsageService: MobileUsageAPI,
                                        private var compositeDisposable: CompositeDisposable,
                                        private var viewModelFactory: MobileDataUsageViewModelFactory): Contract.Presenter {

    private lateinit var viewInstance: View

    override fun onViewResumed() {
        //check for internet connectivity here if required

        val disposable = mobileDataUsageService.getMobileDataUsageData
            .doOnSubscribe {
                viewInstance.showLoadingSpinner()
            }
            .doOnTerminate {
                viewInstance.hideLoadingSpinner()
            }
            .doOnError {
                viewInstance.displayPopup("Error! Please try again!")
            }
            .subscribe {
                handleResponse(it)
            }
        compositeDisposable.add(disposable)
    }

    override fun setView(view: View) {
        this.viewInstance = view
    }

    override fun onViewStopped() {
        compositeDisposable.clear()
    }

    private fun handleResponse(response: MobileDataUsageResponse) {
        val viewModels = viewModelFactory.getViewModels(response.result?.records)
        viewInstance.showDataList(viewModels)
    }
}