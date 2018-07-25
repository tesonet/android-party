package com.playground.ugnius.homework.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.playground.ugnius.homework.R
import com.playground.ugnius.homework.model.entities.Server
import kotlinx.android.synthetic.main.server_cell_entry.view.*

class ServersAdapter(
    context: Context,
    private val entries: List<Server>
) : RecyclerView.Adapter<ServersAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.server_cell_entry, parent, false))
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val entry = entries[position]
        with(viewHolder.itemView) {
            server.text = entry.name
            distance.text = String.format("%s km", entry.distance)
        }
    }
}