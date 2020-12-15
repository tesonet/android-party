package com.example.android_party.ui.servers

import com.example.android_party.App
import com.example.android_party.api.TesonetApi
import com.example.android_party.data.AppDatabase
import com.example.android_party.data.Server
import io.reactivex.Observable

class ServersRepository() {

    private val database: AppDatabase = App.database!!

    fun storeServersList(servers: List<Server>) {
        database.serversDao().delete()
        database.serversDao().insertAll(servers)
    }

    fun getServerListFromDb(): List<Server> {
        return if (database.serversDao().getAll == null) {
            listOf()
        } else {
            database.serversDao().getAll
        }
    }

    fun deleteServersData() {
        database.serversDao().delete()
    }

    fun getServerListFromApi(api: TesonetApi, token: String): Observable<List<Server>> {
        return api.getServers(token)
    }
}