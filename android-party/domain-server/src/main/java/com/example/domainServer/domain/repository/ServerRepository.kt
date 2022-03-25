package com.example.domainServer.domain.repository

import com.example.domainServer.domain.model.Server
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun fetchServer(): Flow<List<Server>>
}
