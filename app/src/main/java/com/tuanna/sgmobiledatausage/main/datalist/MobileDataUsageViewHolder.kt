package com.tuanna.sgmobiledatausage.main.datalist

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_mobile_data_usage.view.*


class MobileDataUsageViewHolder(private var containerView: View?,
                                var onIconClicked: OnIconClickedListener?) : RecyclerView.ViewHolder(containerView){

    fun bindView(viewModel: MobileDataUsageViewModel) {
        containerView?.let {
            it.message?.text = viewModel.message
            if(viewModel.shouldShowIcon){
                it.icon.visibility = View.VISIBLE
            }else {
                it.icon.visibility = View.GONE
            }
            it.icon.setOnClickListener {
                onIconClicked?.invoke(viewModel.decreaseVolumeMessage)
            }
        }
    }
}

typealias OnIconClickedListener = (String) -> Unit
