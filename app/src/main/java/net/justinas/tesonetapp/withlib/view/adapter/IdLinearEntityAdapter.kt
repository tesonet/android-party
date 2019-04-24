package net.justinas.tesonetapp.withlib.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.minilist.util.AutoUpdatableAdapter
import net.justinas.tesonetapp.withlib.databinding.ItemToListBinding
import kotlin.properties.Delegates

class IdLinearEntityAdapter(private val callbacks: Callbacks? = null) : RecyclerView.Adapter<IdLinearEntityAdapter.ViewHolder>(),
    AutoUpdatableAdapter
{
    interface Callbacks {
        fun onItemClick(view: View, item: IdEntity)
    }

    var items: List<IdEntity> by Delegates.observable(emptyList()) {
            _, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemToListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.item = items.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ItemToListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemClick(it, items[adapterPosition]) }
        }
    }
}