package gj.tesonet.ui.servers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gj.tesonet.R
import gj.tesonet.data.model.Server
import kotlinx.android.synthetic.main.view_server.view.*

class ServerListAdapter: RecyclerView.Adapter<ServerListAdapter.ServerViewHolder>() {

    var list: List<Server>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ServerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView
            get() = itemView.name

        val distance: TextView
            get() = itemView.distance

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_server, parent, false)
        return ServerViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        val server = list?.getOrNull(position) ?: return

        holder.name.text = server.name
        holder.distance.text = holder.itemView.context.getString(R.string.server_distance_value, server.distance)
    }
}