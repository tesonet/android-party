package com.czech.androidparty.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.czech.androidparty.R
import com.czech.androidparty.models.DataList

class DataListAdapter(diffCallback: DataListDiffCallback):
    ListAdapter<DataList, DataListAdapter.DataListViewHolder>(diffCallback){

    class DataListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val distance: TextView = itemView.findViewById(R.id.distance)

        @SuppressLint("SetTextI18n")
        fun bind(data: DataList) {
            name.text = data.name
            distance.text = "${data.distance} KM"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_list_item, parent, false)

        return DataListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

}

object DataListDiffCallback : DiffUtil.ItemCallback<DataList>() {
    override fun areItemsTheSame(oldItem: DataList, newItem: DataList): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataList, newItem: DataList): Boolean {
        return oldItem.name == newItem.name
    }
}