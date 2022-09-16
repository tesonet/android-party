package com.ac.androidparty.servers.domain.usecase

import com.ac.androidparty.servers.data.repository.serverslist.ServersResult

internal interface GetServersUseCase {
    suspend fun getServers(): ServersResult
    suspend fun forceGetServers(): ServersResult
}