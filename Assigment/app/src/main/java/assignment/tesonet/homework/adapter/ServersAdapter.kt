package assignment.tesonet.homework.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import assignment.tesonet.homework.R
import assignment.tesonet.homework.api.response.Server
import assignment.tesonet.homework.databinding.ServerItemBinding

class ServersAdapter(private val context: Context, private val dataList : List<Server>) : RecyclerView.Adapter<ServersAdapter.ServerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ServerItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.server_item, parent, false)
        return ServerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ServerViewHolder(private val binding: ServerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(server: Server) {
            binding.serverItem = server
            binding.executePendingBindings()
        }
    }
}