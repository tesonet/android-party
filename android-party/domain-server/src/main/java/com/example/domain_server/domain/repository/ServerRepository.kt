package com.example.domain_server.domain.repository

import com.example.domain_server.domain.model.Server
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    fun fetchServer(): Flow<List<Server>>
}