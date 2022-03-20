package com.example.domain_server.data.datasource.local

import com.example.domain_server.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.Flow

interface ServerLocalDataSource {
    fun getLocalServerList(): Flow<List<ServerEntity>>
    fun saveServerList(servers:List<ServerEntity>):List<Long>
}