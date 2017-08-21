package lt.marius.testio.adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.marius.testio.R

/**
 * Created by marius on 17.3.3.
 */
open class DelegateAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), MutableList<DisplayableItem> {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return BasicViewHolder(LayoutInflater
				                       .from(parent.context)
				                       .inflate(ViewType.values()[viewType].layoutId,
				                                parent,
				                                false))
	}


	protected val items = mutableListOf<DisplayableItem>()

	override fun getItemCount(): Int {
		return items.size
	}

	override fun getItemViewType(position: Int): Int {
		return items[position].getViewType().ordinal
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = items[position]
		item.bind(holder)
	}

	override val size: Int
		get() = items.size //To change initializer of created properties use File | Settings | File Templates.

	override fun contains(element: DisplayableItem): Boolean {
		return items.contains(element)
	}

	override fun containsAll(elements: Collection<DisplayableItem>): Boolean {
		return items.containsAll(elements)
	}

	override fun get(index: Int): DisplayableItem {
		return items[index]
	}

	override fun indexOf(element: DisplayableItem): Int {
		return items.indexOf(element)
	}

	override fun isEmpty(): Boolean {
		return items.isEmpty()
	}

	override fun iterator(): MutableIterator<DisplayableItem> {
		return items.iterator()
	}

	override fun lastIndexOf(element: DisplayableItem): Int {
		return items.lastIndexOf(element)
	}

	override fun add(element: DisplayableItem): Boolean {
		val returnVal = items.add(element)
		notifyItemInserted(items.size)
		return returnVal
	}

	override fun add(index: Int, element: DisplayableItem) {
		items.add(index, element)
		notifyItemInserted(index)
	}

	override fun addAll(index: Int, elements: Collection<DisplayableItem>): Boolean {
		val returnVal = items.addAll(index, elements)
		notifyItemRangeInserted(index, elements.size)
		return returnVal
	}


	override fun addAll(elements: Collection<DisplayableItem>): Boolean {
		val index = items.size + 1
		val returnVal = items.addAll(elements)
		notifyItemRangeInserted(index, elements.size)
		return returnVal
	}

	override fun clear() {
		val count = items.size
		items.clear()
		notifyItemRangeRemoved(0, count)
	}

	override fun listIterator(): MutableListIterator<DisplayableItem> {
		return items.listIterator()
	}

	override fun listIterator(index: Int): MutableListIterator<DisplayableItem> {
		return items.listIterator(index)
	}

	override fun remove(element: DisplayableItem): Boolean {
		val index = items.indexOf(element)
		return if (index != -1) {
			val retVal = items.remove(element)
			notifyItemRemoved(index)
			retVal
		} else {
			false
		}
	}

	override fun removeAll(elements: Collection<DisplayableItem>): Boolean {
		val index = items.indexOf(elements.first())
		val count = elements.count()
		val retVal = items.removeAll(elements)
		notifyItemRangeRemoved(index, count)
		return retVal
	}


	override fun removeAt(index: Int): DisplayableItem {
		val retVal = items.removeAt(index)
		notifyItemRemoved(index)
		return retVal
	}

	override fun retainAll(elements: Collection<DisplayableItem>): Boolean {
		return items.retainAll(elements)
	}


	override fun set(index: Int, element: DisplayableItem): DisplayableItem {
		val retVal = items.set(index, element)
		notifyItemChanged(index)
		return retVal
	}

	override fun subList(fromIndex: Int, toIndex: Int): MutableList<DisplayableItem> {
		return items.subList(fromIndex, toIndex)
	}

}

interface DisplayableItem {
	fun getViewType(): ViewType
	fun bind(holder: RecyclerView.ViewHolder)
}


enum class ViewType(@LayoutRes val layoutId: Int) {
	SERVER(R.layout.item_server),
}

class BasicViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)