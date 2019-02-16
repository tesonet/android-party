package lt.petraslabutis.testio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_entry.view.*
import lt.petraslabutis.testio.R
import lt.petraslabutis.testio.entities.ServerItem
import lt.petraslabutis.testio.extensions.*

class ServerListAdapter : RecyclerView.Adapter<ServerListAdapter.ViewHolder>() {

    private companion object {
        const val TOP_ENTRY_VIEW_TYPE = 1
        const val ENTRY_VIEW_TYPE = 2
        const val BOTTOM_ENTRY_VIEW_TYPE = 3
    }

    private var items: List<ServerItem> = listOf()
    private var longestTextWidth: Int = 0

    fun update(newItems: List<ServerItem>, longestTextWidth: Int) {
        this.longestTextWidth = longestTextWidth
        DiffUtil.calculateDiff(DiffCallback(items, newItems)).dispatchUpdatesTo(this)
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_entry, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            if (holder.itemViewType == TOP_ENTRY_VIEW_TYPE) setTopPadding(context.dp(10F).toInt())
            if (holder.itemViewType == BOTTOM_ENTRY_VIEW_TYPE) separator.gone()
            items.getOrNull(position)?.let {
                name.text = it.name
                distance.text = it.distance
                distance.setRightPadding(longestTextWidth - it.distance.widthInPx(distance))
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> TOP_ENTRY_VIEW_TYPE
            items.size - 1 -> BOTTOM_ENTRY_VIEW_TYPE
            else -> ENTRY_VIEW_TYPE
        }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private inner class DiffCallback(
        private val oldList: List<ServerItem>,
        private val newList: List<ServerItem>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean  =
            oldList.getOrNull(oldItemPosition)?.equals(newList.getOrNull(newItemPosition)) ?: false

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList.getOrNull(oldItemPosition)
            val newItem = newList.getOrNull(newItemPosition)
            return oldItem?.distance == newItem?.distance && oldItem?.name == newItem?.name
        }

    }


}