package com.ac.androidparty.servers.data.repository.cachedserverslist

import com.ac.androidparty.servers.data.remote.ServerResponse

internal interface CachedServersListRepository {
    suspend fun putServersList(servers: List<ServerResponse>)

    suspend fun getServersList(): List<ServerResponse>
}