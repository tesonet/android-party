package com.example.domainServer.data.repository

import com.example.domainServer.data.datasource.local.ServerLocalDataSource
import com.example.domainServer.data.datasource.remote.ServerRemoteDataSource
import com.example.domainServer.data.mapper.DtoToEntityMapper
import com.example.domainServer.data.mapper.EntityToDomainMapper
import com.example.domainServer.domain.model.Server
import com.example.domainServer.domain.repository.ServerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val remoteDataSource: ServerRemoteDataSource,
    private val localDataSource: ServerLocalDataSource
) : ServerRepository {
    private val dtoToEntityMapper = DtoToEntityMapper()
    private val entityToDomain = EntityToDomainMapper()
    override fun fetchServer(): Flow<List<Server>> {
        return remoteDataSource.fetchServerList().map { serverDtoList ->
            localDataSource.saveServerList(
                serverDtoList.map { dtoToEntityMapper.map(it) }
            )
        }.flatMapMerge { list ->
            if (list.isNotEmpty()) {
                fetchLocalServer()
            } else {
                flow { emptyList<Server>() }
            }
        }
    }

    private fun fetchLocalServer(): Flow<List<Server>> {
        return localDataSource.getLocalServerList().map { serverEntityList ->
            serverEntityList.map { serverEntity ->
                entityToDomain.map(serverEntity)
            }
        }
    }
}
