package com.ac.androidparty.servers.data.repository

import com.ac.androidparty.servers.data.remote.Server

internal interface ServersListRepository {
    suspend fun getServers(): ServersResult
}

internal sealed interface ServersResult {
    data class Success(val servers: List<Server>) : ServersResult
    object Error : ServersResult
}