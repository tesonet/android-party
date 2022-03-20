package com.example.domainServer.data.datasource.remote

import com.example.domainServer.data.dto.ServerDto
import com.example.domainServer.data.service.ServerListService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ServerRemoteDataSourceImpl @Inject constructor(
    private val service: ServerListService
) : ServerRemoteDataSource {
    override fun fetchServerList(): Flow<List<ServerDto>> = flow {
        emit(service.fetchServerList())
    }
}
