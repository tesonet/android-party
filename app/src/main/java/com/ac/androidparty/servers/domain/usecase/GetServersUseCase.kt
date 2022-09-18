package com.ac.androidparty.servers.domain.usecase

import com.ac.androidparty.servers.data.repository.cachedserverslist.CachedServersListRepository
import com.ac.androidparty.servers.data.repository.serverslist.ServersListRepository
import com.ac.androidparty.servers.data.repository.serverslist.ServersResult
import javax.inject.Inject

internal interface GetServersUseCase {
    suspend fun getServers(): ServersResult
}

internal class GetServersUseCaseImpl @Inject constructor(
    private val serversListRepository: ServersListRepository,
    private val cachedServersListRepository: CachedServersListRepository
) : GetServersUseCase {
    override suspend fun getServers() =
        with(serversListRepository.getServers()) {
            when (this) {
                is ServersResult.Success -> {
                    cachedServersListRepository.putServersList(serverResponses)
                    this
                }
                is ServersResult.Error ->
                    copy(serverResponses = cachedServersListRepository.getServersList())
            }
        }
}