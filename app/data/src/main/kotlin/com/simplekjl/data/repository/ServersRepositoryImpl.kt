package com.simplekjl.data.repository

import com.simplekjl.data.mapper.toModel
import com.simplekjl.data.model.LoginRaw
import com.simplekjl.domain.model.Login
import com.simplekjl.domain.model.ServerDetails
import com.simplekjl.domain.repository.ServersRepository
import com.simplekjl.domain.utils.Result

class ServersRepositoryImpl(private val networkSource: NetworkSource) : ServersRepository {
    override suspend fun getAllServers(): Result<List<ServerDetails>> {
        return try {
            val call = networkSource.getClient().getServers()
            if (call.isSuccessful) {
                Result.Success(call.body().toModel())
            } else {
                Result.Error(Exception(call.message()))
            }
        } catch (ex: Exception) {
            Result.Error(Exception(ex.message))
        }
    }

    override suspend fun login(credentials: Login): Result<String> {
        return try {
            val call = networkSource.getClient()
                .login(LoginRaw(credentials.username, credentials.password))
            if (call.isSuccessful) {
                Result.Success(call.body()!!.token)
            } else {
                Result.Error(Exception(call.message()))
            }
        } catch (ex: Exception) {
            Result.Error(Exception(ex.message))
        }
    }
}
