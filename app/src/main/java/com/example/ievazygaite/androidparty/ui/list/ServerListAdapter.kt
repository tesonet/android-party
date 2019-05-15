package com.example.ievazygaite.androidparty.ui.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ievazygaite.androidparty.R
import com.example.ievazygaite.androidparty.data.server.Server
import kotlinx.android.synthetic.main.item_layout.view.*

class ServerListAdapter(private val serverList: ArrayList<Server>, private val context: Context) :
    RecyclerView.Adapter<ServerListAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(serverList[position])
    }

    override fun getItemCount(): Int = serverList.size

    fun setData(servers: List<Server>) {
        serverList.clear()
        serverList.addAll(servers)
        notifyDataSetChanged()
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(server: Server) {
            itemView.distance.text = context.getString(R.string.distance_in_km, server.distance)
            itemView.server_name.text = server.serverName
        }
    }

}