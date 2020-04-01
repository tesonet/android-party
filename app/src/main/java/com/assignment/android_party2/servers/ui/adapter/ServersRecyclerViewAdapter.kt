package com.assignment.android_party2.servers.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.assignment.android_party2.R
import com.assignment.android_party2.servers.models.ServerModel

class ServersRecyclerViewAdapter(private val context: Activity, private val servers: List<ServerModel>) :
    RecyclerView.Adapter<ServersRecyclerViewAdapter.ServerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.server_item, parent, false)
        return ServerViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        val server = servers[position]
        holder.name.text = server.name
        holder.distance.text = server.distance.toString()+ "  km"
    }

    override fun getItemCount(): Int {
        return servers.size
    }

    inner class ServerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var distance: TextView = itemView.findViewById(R.id.distance)

    }
}
