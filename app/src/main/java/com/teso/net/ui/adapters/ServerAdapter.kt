package com.teso.net.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teso.net.R
import com.teso.net.data_flow.database.entities.ServerEntity
import kotlinx.android.synthetic.main.list_item.view.*


class ServerAdapter(private val items: List<ServerEntity>, private val listener: (ServerEntity) -> Unit) : RecyclerView.Adapter<ServerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ServerEntity, listener: (ServerEntity) -> Unit) = with(itemView) {
            serverName.text = item.name
            serverDistance.text = item.distance.toString().plus(" ").plus(context.getString(R.string.km))
            setOnClickListener { listener(item) }
        }
    }
}