package com.amaximan.tesonet.other.adapters.rv

import com.amaximan.tesonet.R
import com.amaximan.tesonet.model.database.server.Server

class ServersRVAdapter : GenericRVAdapter<Server>() {
    override fun getLayoutResId() = R.layout.list_item_server

    override fun getViewModel(position: Int) = data[position]
}