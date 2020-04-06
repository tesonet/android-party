package com.example.androidparty

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidparty.db.ServerEntity
import kotlinx.android.synthetic.main.server_item_layout.view.*

class ServerListAdapter(private val context: Context) :
    RecyclerView.Adapter<ServerListAdapter.ServerViewHolder>() {

    var serverList: List<ServerEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val inflatedView =
            LayoutInflater.from(context).inflate(R.layout.server_item_layout, parent, false)
        return ServerViewHolder(inflatedView)
    }

    fun setList(serverList: List<ServerEntity>) {
        this.serverList = serverList
    }

    override fun getItemCount() = serverList.size

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.bind(serverList[position])
    }

    class ServerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(serverData: ServerEntity) {
            view.viewTextViewCountry.text = serverData.name
            view.viewTextViewDistance.text = String.format("%s km", serverData.distance)
        }
    }
}