package com.example.domainServer.data.datasource.local

import com.example.domainServer.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.Flow

interface ServerLocalDataSource {
    fun getLocalServerList(): Flow<List<ServerEntity>>
    fun saveServerList(servers: List<ServerEntity>): List<Long>
}
