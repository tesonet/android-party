package com.tesonet.testio.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tesonet.testio.R
import com.tesonet.testio.databinding.ServerListItemBinding
import com.tesonet.testio.services.data.server.Server
import com.tesonet.testio.ui.adapters.ServersAdapter.ViewHolder
import com.tesonet.testio.utils.addKilometers

class ServersAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val servers = ArrayList<Server>()
    var selectedServer: ((Server) -> Unit) = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ServerListItemBinding.inflate(LayoutInflater.from(context), parent, false).root)
    }

    override fun getItemCount(): Int {
        return servers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val server = servers[position]
        holder.run {
            serverName.text = server.name
            serverDistance.text = server.distance.addKilometers()

            itemView.setOnClickListener {
                selectedServer(server)
            }
        }
    }

    fun setItem(servers: List<Server>?) {
        this.servers.clear()
        servers?.let {
            this.servers.addAll(it)
        }

        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val serverName: TextView = view.findViewById(R.id.textViewServerName)
        val serverDistance: TextView = view.findViewById(R.id.textViewServerDistance)
    }
}