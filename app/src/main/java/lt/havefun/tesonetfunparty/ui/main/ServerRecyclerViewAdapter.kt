package lt.havefun.tesonetfunparty.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lt.havefun.tesonetfunparty.R
import lt.havefun.tesonetfunparty.data.Server

class ServerRecyclerViewAdapter(
    private val values: List<Server>
) : RecyclerView.Adapter<ServerRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.server_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        with(item) {
            holder.nameView.text = name
            holder.distanceView.text = "$distance".plus(" km")
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name_tv)
        val distanceView: TextView = view.findViewById(R.id.distance_tv)
    }
}
