package com.demo.androidparty.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.androidparty.R
import com.demo.androidparty.dto.Server
import kotlinx.android.synthetic.main.server_list_item.view.*

class ServerListAdapter : RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {

    private val servers = mutableListOf<Server>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.server_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = servers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val server = servers[position]
        holder.bind(server)
    }

    internal fun setData(data: List<Server>) {
        servers.clear()
        servers.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        internal fun bind(server: Server) {
            view.name.text = server.name
            view.distance.text = server.distance.toString()
        }
    }
}