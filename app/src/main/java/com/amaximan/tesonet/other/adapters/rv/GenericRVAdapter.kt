package com.amaximan.tesonet.other.adapters.rv

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.amaximan.tesonet.BR
import com.amaximan.tesonet.model.database.base.BaseEntity
import com.amaximan.tesonet.view.contracts.RequiresLayoutRes

abstract class GenericRVAdapter<T : BaseEntity> : RecyclerView.Adapter<BindableViewHolder>(), RequiresLayoutRes {
    protected var data = ArrayList<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder = BindableViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), getLayoutResId(), parent, false))

    override fun getItemCount() = data.size

    fun setData(newData: List<T>) {
        val postDiffCallback = PostDiffCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(postDiffCallback)
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    abstract fun getViewModel(position: Int): Any?

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.binding.setVariable(BR.viewModel, getViewModel(position))
    }

    fun clearAll() {
        data.clear()
        notifyDataSetChanged()
    }
}