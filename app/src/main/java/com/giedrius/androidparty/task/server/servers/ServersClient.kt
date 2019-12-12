package com.giedrius.androidparty.task.server.servers

import com.giedrius.androidparty.task.model.Server
import com.giedrius.androidparty.task.utils.ApiListener

interface ServersClient {
    fun getServersList(apiListener: ApiListener<List<Server>>)
}