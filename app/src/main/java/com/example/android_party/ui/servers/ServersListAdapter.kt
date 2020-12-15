package com.example.android_party.ui.servers

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_party.R
import com.example.android_party.data.Server

class ServersListAdapter(private val context: Activity) :
    RecyclerView.Adapter<ServersListAdapter.ServerViewHolder>() {

    private var serversList: List<Server> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val rootView =
            LayoutInflater.from(context).inflate(R.layout.servers_list_item, parent, false)
        return ServerViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        val units = " km"
        val server = serversList[position]
        holder.name.text = server.name
        holder.distance.text = String.format("%s %s", server.distance, units)
    }

    override fun getItemCount(): Int {
        return serversList.size
    }

    fun updateList(servers: List<Server>) {
        serversList = servers
        notifyDataSetChanged()
    }

    class ServerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.server_name)
        var distance: TextView = itemView.findViewById(R.id.server_distance)
    }
}