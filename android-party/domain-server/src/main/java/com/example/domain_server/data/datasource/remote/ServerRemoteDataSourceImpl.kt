package com.example.domain_server.data.datasource.remote

import com.example.domain_server.data.dto.ServerDto
import com.example.domain_server.data.service.ServerListService
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