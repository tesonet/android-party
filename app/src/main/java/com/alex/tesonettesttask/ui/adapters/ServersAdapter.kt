package com.alex.tesonettesttask.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.tesonettesttask.R
import com.alex.tesonettesttask.logic.entities.Server
import kotlinx.android.synthetic.main.server_item.view.*

class ServersAdapter(private val servers: List<Server>) : RecyclerView.Adapter<ServersAdapter.ServerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        return ServerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.server_item, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ServerViewHolder, position: Int) {
        val serverItem = servers[position]
        with(viewHolder.itemView) {
            server.text = serverItem.name
            distance.text = String.format("%s km", serverItem.distance)
            divider.visibility = if (position == servers.size - 1) View.GONE else View.VISIBLE
        }
    }

    override fun getItemCount() = servers.size

    inner class ServerViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}