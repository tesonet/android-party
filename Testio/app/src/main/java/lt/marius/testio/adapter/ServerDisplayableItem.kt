package lt.marius.testio.adapter

import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.item_server.view.*
import lt.marius.testio.model.Server


/**
 * Created by marius on 17.5.16.
 */
class ServerDisplayableItem(val server: Server) : DisplayableItem {
	override fun getViewType() = ViewType.SERVER
	override fun bind(holder: RecyclerView.ViewHolder) {
		holder.itemView.apply {
			serverName.text = server.name
			serverDistance.text = "${server.distance} km"
		}
	}
}