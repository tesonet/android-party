package com.baruckis.androidparty.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baruckis.androidparty.ui.databinding.ListItemServerBinding
import com.baruckis.androidparty.ui.model.ServerUi
import javax.inject.Inject

class ServersListRecyclerViewAdapter @Inject constructor() :
    RecyclerView.Adapter<ServersListRecyclerViewAdapter.BindingViewHolder>() {

    private var dataList: List<ServerUi> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemServerBinding.inflate(inflater, parent, false)

        return BindingViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) =
        holder.bind(dataList[position])

    fun setData(newDataList: List<ServerUi>) {
        dataList = newDataList
        notifyDataSetChanged()
    }


    inner class BindingViewHolder(private var binding: ListItemServerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(serverUi: ServerUi) {

            binding.serverUi = serverUi

            binding.executePendingBindings()
        }

    }

}