package com.thescriptan.tesonetparty.list.repository

import com.thescriptan.tesonetparty.list.model.ServerInfo
import com.thescriptan.tesonetparty.network.ListApi
import com.thescriptan.tesonetparty.storage.TesoDataStore
import com.thescriptan.tesonetparty.utils.Result
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface ListRepository {
    suspend fun getServers(): Result<List<ServerInfo>>
    suspend fun logout()
}

@ActivityScoped
class ListRepositoryImpl @Inject constructor(
    private val api: ListApi,
    private val dataStore: TesoDataStore
) : ListRepository {
    override suspend fun getServers(): Result<List<ServerInfo>> {
        val response = try {
            val token = dataStore.token.first()
            if (token.isEmpty()) {
                return Result.Error("No token. Please log in again.")
            }
            api.getServers(token)
        } catch (e: Exception) {
            return Result.Error("Network error ${e.message}")
        }

        if (response.code() == 200) {
            response.body()?.let { it ->
                //TODO: save in Room and determine check if refetching is required
                return Result.Success(it.sortedBy { s -> s.distance })
            }
        } else if (response.code() == 401) {
            return Result.Error("Authentication error")
        }
        return Result.Error("Unknown error")
    }

    override suspend fun logout() {
        dataStore.setToken("")
    }
}