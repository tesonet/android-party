package com.demo.androidparty.ui.list

import com.demo.androidparty.di.annotations.IO
import com.demo.androidparty.dto.ServerModel
import com.demo.androidparty.network.ApiService
import com.demo.androidparty.storage.database.ServerDao
import com.demo.androidparty.storage.preferences.AppPreferences
import com.demo.androidparty.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ServerListModel(
    @IO private val dispatcher: CoroutineDispatcher,
    private val apiService: ApiService,
    private val preferences: AppPreferences,
    private val dao: ServerDao
) {

    internal suspend fun fetchRemoteServersData(): NetworkResult<List<ServerModel>> =
        withContext(dispatcher) {
            val response = apiService.fetchServers()
            if (response.isSuccessful) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.code())
            }
        }

    internal suspend fun fetchDatabaseServersData(): List<ServerModel> = withContext(dispatcher) {
        dao.getAll()
    }

    internal suspend fun saveServersData(serversList: List<ServerModel>) = withContext(dispatcher) {
        dao.deleteAll()
        dao.saveAll(serversList)
    }

    internal suspend fun clearData() = withContext(dispatcher) {
        preferences.clear()
        dao.deleteAll()
    }
}