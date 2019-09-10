package com.jonastiskus.testio.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonastiskus.testio.model.ServerGsonModel
import com.jonastiskus.testio.databinding.ViewItemServerBinding
import com.jonastiskus.testio.databinding.BindingViewHolder

class  ServerListAdapter : RecyclerView.Adapter<BindingViewHolder<ViewItemServerBinding>>() {

    private var dataList: List<ServerGsonModel> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<ViewItemServerBinding> {
        return BindingViewHolder(
            ViewItemServerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: BindingViewHolder<ViewItemServerBinding>, position: Int) {
        val viewItemServerBinding = holder.binding
        viewItemServerBinding.data = dataList[position]
    }

    fun setData(data : List<ServerGsonModel>) {
        this.dataList = data
    }

}