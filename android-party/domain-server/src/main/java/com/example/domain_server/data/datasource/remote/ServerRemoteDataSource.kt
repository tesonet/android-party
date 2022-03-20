package com.example.domain_server.data.datasource.remote

import com.example.domain_login.data.dto.LoginDto
import com.example.domain_login.data.dto.TokenDto
import com.example.domain_server.data.dto.ServerDto
import kotlinx.coroutines.flow.Flow

interface ServerRemoteDataSource {
    fun fetchServerList(): Flow<List<ServerDto>>
}