package com.example.domain_server.data.datasource.local

import com.example.domain_server.data.db.dao.ServerDao
import com.example.domain_server.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ServerLocalDataSourceImpl @Inject constructor(
    private val dao: ServerDao
) : ServerLocalDataSource {
    override fun getLocalServerList(): Flow<List<ServerEntity>> {
        return dao.fetchAllServers()
    }

    override fun saveServerList(servers: List<ServerEntity>): List<Long> =
        dao.insertServers(servers)
}