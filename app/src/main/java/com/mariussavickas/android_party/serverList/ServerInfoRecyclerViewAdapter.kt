package com.mariussavickas.android_party.serverList

import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mariussavickas.android_party.R
import com.mariussavickas.android_party.persistance.ServerInfo

class ServerInfoRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data: List<ServerInfo> = arrayListOf(ServerInfo("Test1", 1), ServerInfo("Test2", 2))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ServerInfoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.sever_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ServerInfoViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private class ServerInfoViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal fun bind(info: ServerInfo) {
            (itemView.findViewById<TextView>(R.id.tv_server_list_item_distance)).text = info.distance.toString() + " km"
            (itemView.findViewById<TextView>(R.id.tv_server_list_item_server)).text = info.name
        }
    }
}