package levkovskiy.dev.tesonettest.ui.main

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_server.view.country
import kotlinx.android.synthetic.main.item_server.view.distance
import levkovskiy.dev.tesonettest.R
import levkovskiy.dev.tesonettest.net.Model
import levkovskiy.dev.tesonettest.net.Model.Server

class MainAdapter(var list: ArrayList<Server>, private val listener: (Server) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
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
    fun bind(item: Server, listener: (Server) -> Unit) = with(itemView) {
      country.text = item.name
      distance.text = item.distance.toString()
      setOnClickListener { listener(item) }
    }
  }
}