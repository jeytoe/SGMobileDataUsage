package com.tuanna.sgmobiledatausage.main.datalist

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_mobile_data_usage.view.*


class MobileDataUsageViewHolder(private var containerView: View?) : RecyclerView.ViewHolder(containerView){

    fun bindView(viewModel: MobileDataUsageViewModel) {
        containerView?.message?.text = viewModel.message
    }
}

