package com.tesonet.testio.ui.serverslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesonet.testio.R
import com.tesonet.testio.data.local.entity.Server
import kotlinx.android.synthetic.main.item_serverslist.view.*


class ServersListAdapter : RecyclerView.Adapter<ServersListAdapter.ViewHolder>() {

    var servers = emptyList<Server>()

    class ViewHolder(val layout: ViewGroup) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_serverslist, parent, false) as ViewGroup
        return ViewHolder(layout)
    }

    override fun getItemCount() = servers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layout.server.text = servers[position].name
        holder.layout.distance.text = holder.layout.context.getString(R.string.km, servers[position].distance)
    }
}