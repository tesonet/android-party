package com.ac.androidparty.servers.data.repository.serverslist

import com.ac.androidparty.servers.data.remote.ServerResponse
import com.ac.androidparty.servers.data.remote.toServer
import com.ac.androidparty.servers.presentation.ServersListState

internal interface ServersListRepository {
    suspend fun getServers(): ServersResult
}

internal sealed interface ServersResult {
    data class Success(val serverResponses: List<ServerResponse>) : ServersResult
    data class Error(val serverResponses: List<ServerResponse> = emptyList()) : ServersResult
}

internal fun ServersResult.asServersListState() = when (this) {
    is ServersResult.Success -> ServersListState.Success(serverResponses.map(ServerResponse::toServer))
    is ServersResult.Error -> ServersListState.Error(serverResponses.map(ServerResponse::toServer))
}