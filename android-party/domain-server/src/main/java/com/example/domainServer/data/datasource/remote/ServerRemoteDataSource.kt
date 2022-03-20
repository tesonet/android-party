package com.example.domainServer.data.datasource.remote

import com.example.domainServer.data.dto.ServerDto
import kotlinx.coroutines.flow.Flow

interface ServerRemoteDataSource {
    fun fetchServerList(): Flow<List<ServerDto>>
}
