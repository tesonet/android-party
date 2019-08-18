package com.example.androidparty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidparty.R
import com.example.androidparty.databinding.ServerListItemBinding
import com.example.androidparty.viewmodel.ServerViewModel

class ServersAdapter(private var list: List<ServerViewModel>) : RecyclerView.Adapter<ServersAdapter.ServerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ServerListItemBinding = DataBindingUtil.inflate(inflater, R.layout.server_list_item, parent, false)
        return ServerViewHolder(binding)
    }

    fun setData(data: List<ServerViewModel>){
        list = data
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        val item: ServerViewModel = list[position]
        holder.bind(item)
    }

    class ServerViewHolder(private val binding: ServerListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ServerViewModel) {
            binding.server = item
            binding.executePendingBindings()
        }
    }
}