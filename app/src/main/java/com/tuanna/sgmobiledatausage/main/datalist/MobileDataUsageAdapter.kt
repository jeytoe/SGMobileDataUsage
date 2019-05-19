package com.tuanna.sgmobiledatausage.main.datalist

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tuanna.sgmobiledatausage.R
import java.util.ArrayList
import javax.inject.Inject

class MobileDataUsageAdapter @Inject constructor(): Adapter<RecyclerView.ViewHolder>() {

    var viewModels: List<MobileDataUsageViewModel> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mobile_data_usage, parent, false)
        return MobileDataUsageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MobileDataUsageViewHolder).bindView(viewModels[position])
    }
}