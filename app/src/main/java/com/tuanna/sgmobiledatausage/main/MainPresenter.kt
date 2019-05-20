package com.tuanna.sgmobiledatausage.main

import com.tuanna.sgmobiledatausage.database.RecordProvider
import com.tuanna.sgmobiledatausage.database.ResponseWrapper
import com.tuanna.sgmobiledatausage.main.Contract.View
import com.tuanna.sgmobiledatausage.main.datalist.MobileDataUsageViewModelFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(private var compositeDisposable: CompositeDisposable,
                                        private var viewModelFactory: MobileDataUsageViewModelFactory,
                                        private var recordProvider: RecordProvider): Contract.Presenter {

    private lateinit var viewInstance: View

    override fun onViewResumed(hasNetworkConnection: Boolean) {
        val responseWrapper: Observable<ResponseWrapper> = if (hasNetworkConnection) {
            recordProvider.getRecordsFromAPI()
        } else {
            recordProvider.getRecordsFromDatabase()
        }

        val disposable = responseWrapper
            .doOnSubscribe {
                viewInstance.showLoadingSpinner()
            }
            .doOnTerminate {
                viewInstance.hideLoadingSpinner()
            }
            .subscribe ({
                handleResponse(it)
            }, {
                viewInstance.displayPopup("Error! Please try again!")
            })
        compositeDisposable.add(disposable)
    }

    override fun setView(view: View) {
        this.viewInstance = view
    }

    override fun onViewStopped() {
        compositeDisposable.clear()
    }

    private fun handleResponse(response: ResponseWrapper) {
        val viewModels = viewModelFactory.getViewModels(response.records)
        viewInstance.showDataList(viewModels)
    }
}