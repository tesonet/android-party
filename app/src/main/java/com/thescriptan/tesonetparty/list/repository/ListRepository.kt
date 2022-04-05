package com.thescriptan.tesonetparty.list.repository

import com.thescriptan.tesonetparty.storage.TesoDataStore
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

interface ListRepository {
    suspend fun logout()
}

@ActivityScoped
class ListRepositoryImpl @Inject constructor(private val dataStore: TesoDataStore): ListRepository {

    override suspend fun logout() {
        dataStore.setToken("")
    }
}