package com.giedrius.androidparty.task.api.servers

import com.giedrius.androidparty.task.viewmodel.Server
import com.giedrius.androidparty.task.utils.ApiListener

interface ServersClient {
    fun getServersList(apiListener: ApiListener<List<Server>>)
}