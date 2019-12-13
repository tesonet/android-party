package com.giedrius.androidparty.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.giedrius.androidparty.R
import com.giedrius.androidparty.task.utils.Constants
import com.giedrius.androidparty.task.viewmodel.ServerViewModel
import kotlinx.android.synthetic.main.server_item.view.*

class ServerAdapter(private var servers: List<ServerViewModel>) :
    RecyclerView.Adapter<ServerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(servers[position])
    }

    override fun getItemCount(): Int = servers.count()

    fun setData(data: List<ServerViewModel>) {
        servers = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.server_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ServerViewModel) {
            itemView.server_name.text = item.name
            itemView.server_distance.text = item.distance.toString()
            itemView.server_distance_units.text = item.distanceUnits
        }
    }
}