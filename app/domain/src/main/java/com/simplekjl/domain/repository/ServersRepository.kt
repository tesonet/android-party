package com.simplekjl.domain.repository

import com.simplekjl.domain.model.Login
import com.simplekjl.domain.model.ServerDetails
import com.simplekjl.domain.utils.Result

interface ServersRepository {
    suspend fun getAllServers(): Result<List<ServerDetails>>
    suspend fun login(credentials: Login): Result<String>
}
