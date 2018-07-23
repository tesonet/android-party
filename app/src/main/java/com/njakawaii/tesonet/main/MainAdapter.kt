package com.njakawaii.tesonet.main

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.njakawaii.tesonet.R
import com.njakawaii.tesonet.data.ServerModel
import kotlinx.android.synthetic.main.item_server.view.*

class MainAdapter(var list: ArrayList<ServerModel>, private val listener: (ServerModel) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
  override fun onCreateViewHolder(
    parent: ViewGroup,
    p1: Int
  ): ViewHolder {
    val inflatedView = parent.inflate(R.layout.item_server, false)
    return ViewHolder(inflatedView)
  }
  fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
  }
  override fun getItemCount() = list.size

  override fun onBindViewHolder(
          holder: ViewHolder,
          position: Int
  ) {
    holder.bind(list[position], listener)
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(item: ServerModel, listener: (ServerModel) -> Unit) = with(itemView) {
      country.text = item.name
      distance.text = item.distance.toString()
      setOnClickListener { listener(item) }
    }
  }
}