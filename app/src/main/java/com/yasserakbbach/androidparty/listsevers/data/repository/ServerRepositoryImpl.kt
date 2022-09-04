package com.yasserakbbach.androidparty.listsevers.data.repository

import androidx.room.withTransaction
import com.yasserakbbach.androidparty.listsevers.data.local.AppDatabase
import com.yasserakbbach.androidparty.listsevers.data.local.ServerDao
import com.yasserakbbach.androidparty.listsevers.data.mapper.toServer
import com.yasserakbbach.androidparty.listsevers.data.mapper.toServerEntityList
import com.yasserakbbach.androidparty.listsevers.data.remote.ServerApi
import com.yasserakbbach.androidparty.listsevers.domain.model.Server
import com.yasserakbbach.androidparty.listsevers.domain.repository.ServerRepository
import com.yasserakbbach.androidparty.util.Resource
import com.yasserakbbach.androidparty.util.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ServerRepositoryImpl(
    private val serverApi: ServerApi,
    private val appDatabase: AppDatabase,
) : ServerRepository {

    private val serverDao: ServerDao by lazy {
        appDatabase.serverDao()
    }

    override suspend fun getServers(): Flow<Resource<List<Server>>> =
        networkBoundResource(
            query = {
                serverDao.getServers().map { list ->
                    list.map { it.toServer() }
                }
            },
            fetch = {
                serverApi.getServers()
            },
            saveFetchResult = { response ->
                appDatabase.withTransaction {
                    serverDao.apply {
                        deleteAllServers()
                        insertServer(response.toServerEntityList())
                    }
                }
            }
        )

    override suspend fun getAllServers(): List<Server> =
        serverApi.getServers().map {
            it.toServer()
        }
}