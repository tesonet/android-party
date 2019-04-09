package com.edvinas.balkaitis.party.servers.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.edvinas.balkaitis.party.R

class ServersViewHolderFactory {
    fun create(parent: ViewGroup): ServersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_server, parent, false)
        return ServersViewHolder(view)
    }
}
