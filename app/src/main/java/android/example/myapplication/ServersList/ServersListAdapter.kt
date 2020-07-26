package android.example.myapplication.ServersList

import android.content.Context
import android.example.myapplication.R
import android.example.myapplication.model.Server
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ServerListAdapter(private val context: Context?) :
    RecyclerView.Adapter<ServerListAdapter.ServerViewHolder>() {

    var serverList: List<Server> =emptyList()

    fun setList(serverList: List<Server>) {
        this.serverList=serverList
    }

    //Adapter inflates an item layout for recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val inflatedView=
            LayoutInflater.from(this.context).inflate(R.layout.servers_list_item, parent, false)
        return ServerViewHolder(
            inflatedView
        )
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.bind(serverList[position])
    }

    //In order for the recycler view to know how far to scroll, the adapter must know how many items are available
    override fun getItemCount()=serverList.size


    class ServerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val country: TextView=itemView.findViewById(R.id.viewTextViewCountry)
        private val distance: TextView=itemView.findViewById(R.id.viewTextViewDistance)

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        fun bind(serverData: Server) {
            country.text=serverData.name
            distance.text=String.format("%s km", serverData.distance)
        }
    }
}

