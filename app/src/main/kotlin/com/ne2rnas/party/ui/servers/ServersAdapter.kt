package com.ne2rnas.party.ui.servers

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ne2rnas.party.R
import com.ne2rnas.party.data.servers.Server
import com.ne2rnas.party.databinding.ItemServerBinding

class ServersAdapter(private val context: Context) : RecyclerView.Adapter<ServersAdapter.ServerViewHolder>() {

    private var servers: List<Server> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemServerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_server, parent, false)
        return ServerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.bind(servers[position])
    }

    override fun getItemCount(): Int {
        return servers.size
    }

    fun updateServers(servers: List<Server>) {
        this.servers = servers
        notifyDataSetChanged()
    }

    class ServerViewHolder(private val binding: ItemServerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(server: Server) {
            binding.server = server
            binding.executePendingBindings()
        }
    }
}
