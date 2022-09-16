package com.ac.androidparty.servers.data.repository.mapper

import android.util.Log
import com.ac.androidparty.servers.data.remote.Server
import com.ac.androidparty.servers.data.repository.ServersResult

internal object ServersResultMapper {
    operator fun invoke(servers: List<Server>): ServersResult = try {
        ServersResult.Success(servers)
    } catch (throwable: Throwable) {
        Log.w("ServersResultMapper", throwable.toString())
        ServersResult.Error
    }
}