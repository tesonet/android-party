package place.holder.androidparty.servers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_server.view.*
import place.holder.androidparty.R
import place.holder.androidparty.servers.model.Server

class ServerListAdapter : RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {

    var servers: List<Server> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_server, parent, false)
        view.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                parent.resources.getDimensionPixelSize(R.dimen.servers_item_height))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = servers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            nameTextView.text = servers[position].name
            distanceTextView.text = itemView.context.getString(
                    R.string.servers_item_distance, servers[position].distance)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val distanceTextView: TextView = view.distanceTextView
    }
}