package com.edvinas.balkaitis.party.servers.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.edvinas.balkaitis.party.R
import com.edvinas.balkaitis.party.servers.network.Server
import kotlinx.android.synthetic.main.item_server.view.*

class ServersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(server: Server) {
        itemView.country.text = server.country
        val distanceLabelId = R.string.server_item_label_distance
        itemView.distance.text = view.context.getString(distanceLabelId, server.distance)
    }
}
