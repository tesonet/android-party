package com.ac.androidparty.servers.data.repository.mapper

import com.ac.androidparty.servers.data.remote.ServerResponse
import com.ac.androidparty.servers.data.repository.ServersResult
import timber.log.Timber

internal object ServersResultMapper {
    operator fun invoke(serverResponses: List<ServerResponse>): ServersResult = try {
        ServersResult.Success(serverResponses)
    } catch (throwable: Throwable) {
        Timber.tag("ServersResultMapper").w(throwable.toString())
        ServersResult.Error
    }
}