package com.tesonet.testio.data.repository

import com.tesonet.testio.base.BaseRepository
import com.tesonet.testio.data.local.dao.ServerDao
import com.tesonet.testio.data.local.dao.deleteAllAsync
import com.tesonet.testio.data.local.dao.insertManyAsync
import com.tesonet.testio.data.local.dao.selectAllAsync
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.local.entity.Token
import com.tesonet.testio.data.mapper.ServerMapper
import com.tesonet.testio.data.mapper.TokenMapper
import com.tesonet.testio.data.remote.PlaygroundApi
import com.tesonet.testio.util.NetworkAvailability
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerRepository @Inject constructor(
    private val api: PlaygroundApi,
    private val serverDao: ServerDao,
    private val networkAvailability: NetworkAvailability
): BaseRepository<List<Server>>() {

    fun getServers(token: Token) = loadOrGetCached {
        if (networkAvailability.isNetworkAvailable()) {
            fetchServersFromRemote(token)
        } else {
            fetchServersFromLocalDb()
        }
    }

    private suspend fun fetchServersFromRemote(token: Token): List<Server> {
        val apiServers = api.getServers(TokenMapper.map(token)).await()
        val servers = apiServers.mapIndexed { index, server ->  ServerMapper.map(server, index) }
        serverDao.deleteAllAsync()
        serverDao.insertManyAsync(servers)
        return servers
    }

    private suspend fun fetchServersFromLocalDb() = serverDao.selectAllAsync()
}