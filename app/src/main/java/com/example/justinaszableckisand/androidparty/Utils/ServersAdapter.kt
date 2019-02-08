package com.example.justinaszableckisand.androidparty.Utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.justinaszableckisand.androidparty.Models.Server
import com.example.justinaszableckisand.androidparty.R
import kotlinx.android.synthetic.main.item_server.view.*

class ServersAdapter (private val mContext : Context, private val mServersList: List<Server>)
    : RecyclerView.Adapter<ServersAdapter.ViewHolder>(){

    override fun getItemCount(): Int { return mServersList.size }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_server,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvServerName.text = mServersList[position].name
        holder.tvDistance.text = mServersList[position].distance.toString().plus(" km")
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val tvServerName: TextView = itemView.tvServerName
        val tvDistance: TextView = itemView.tvDistance
    }
}