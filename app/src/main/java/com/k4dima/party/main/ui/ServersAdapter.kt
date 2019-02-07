package com.k4dima.androidparty.features.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.k4dima.androidparty.databinding.ServerItemBinding
import com.k4dima.androidparty.features.main.data.model.Server

class ServersAdapter(val servers: List<Server>) : RecyclerView.Adapter<ServersAdapter.ServersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServersViewHolder {
        val binding = ServerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServersViewHolder(binding)
    }

    override fun getItemCount() = servers.size

    override fun onBindViewHolder(holder: ServersViewHolder, position: Int) {
        holder.binding.run {
            server = servers[position]
            executePendingBindings()
        }
    }

    class ServersViewHolder(val binding: ServerItemBinding) : RecyclerView.ViewHolder(binding.root)
}