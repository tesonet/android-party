package com.example.domainServer.data.datasource.local

import com.example.domainServer.data.db.dao.ServerDao
import com.example.domainServer.data.db.entity.ServerEntity
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
