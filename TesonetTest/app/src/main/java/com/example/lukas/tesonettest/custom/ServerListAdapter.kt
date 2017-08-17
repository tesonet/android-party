package com.example.lukas.tesonettest.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lukas.tesonettest.R
import com.example.lukas.tesonettest.model.Server
import kotlinx.android.synthetic.main.item_server.view.*


/**
 * Created by lukas on 17.8.17.
 */
class ServerListAdapter(val items: List<Server>) : RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {
	// Not use static
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

	override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
		holder?.itemView?.apply {
			server_name.text = items[position].name
			server_distance.text = context.getString(R.string.server_distance,items[position].distance)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent?.context).inflate(
								R.layout.item_server, parent, false)
		val holder = ViewHolder(view)
		return holder
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun onBindViewHolder(holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
		super.onBindViewHolder(holder, position, payloads)
	}

}