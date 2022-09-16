package com.ac.androidparty.servers.data.repository.serverslist

import com.ac.androidparty.servers.data.remote.ServersApi
import com.ac.androidparty.servers.data.repository.serverslist.mapper.ServersResultMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

internal class ServersListRepositoryImpl @Inject constructor(
    private val serversApi: ServersApi
) : ServersListRepository {

    private val mapper: ServersResultMapper = ServersResultMapper

    override suspend fun getServers(): ServersResult =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                RetryExecutor.execute({ execute() }, MAX_ATTEMPTS)
            }.onFailure {
                ServersResult.Error()
            }.getOrNull() ?: ServersResult.Error()
        }

    private suspend fun execute(): ServersResult = mapper(serversApi.getServers())

    private companion object {
        const val MAX_ATTEMPTS = 3
    }
}

private object RetryExecutor {
    private var count = 0

    suspend fun execute(action: suspend () -> ServersResult, attempts: Int): ServersResult {
        count++
        return try {
            action.invoke()
        } catch (throwable: Throwable) {
            Timber.tag("ServersRetryExecutor").w(throwable.toString())
            if (count < attempts) {
                delay(DELAY)
                execute(action, attempts)
            } else {
                ServersResult.Error()
            }
        }
    }

    private const val DELAY = 1000L
}