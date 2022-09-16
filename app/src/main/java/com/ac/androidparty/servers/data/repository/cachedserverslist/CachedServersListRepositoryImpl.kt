package com.ac.androidparty.servers.data.repository.cachedserverslist

import com.ac.androidparty.servers.data.localsource.ServerDao
import com.ac.androidparty.servers.data.localsource.ServerEntity
import com.ac.androidparty.servers.data.localsource.ServersDatabase
import com.ac.androidparty.servers.data.localsource.toServerResponse
import com.ac.androidparty.servers.data.remote.ServerResponse
import com.ac.androidparty.servers.data.remote.toServerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CachedServersListRepositoryImpl @Inject constructor(
    private val serversDao: ServerDao,
    private val serversDatabase: ServersDatabase
) : CachedServersListRepository {
    override suspend fun putServersList(servers: List<ServerResponse>) =
        withContext(Dispatchers.IO) {
            serversDatabase.runInTransaction {
                serversDao.insertAll(servers.map(ServerResponse::toServerEntity))
            }
        }

    override suspend fun getServersList(): List<ServerResponse> =
        withContext(Dispatchers.IO) {
            serversDao.getAll().map(ServerEntity::toServerResponse)
        }
}