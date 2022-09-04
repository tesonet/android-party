package com.yasserakbbach.androidparty.listsevers.domain.repository

import com.yasserakbbach.androidparty.listsevers.domain.model.Server
import com.yasserakbbach.androidparty.util.Resource
import kotlinx.coroutines.flow.Flow

interface ServerRepository {

    suspend fun getServers(): Flow<Resource<List<Server>>>
    suspend fun getAllServers(): List<Server>
}