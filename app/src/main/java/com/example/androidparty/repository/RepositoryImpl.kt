package com.example.androidparty.repository

import android.content.SharedPreferences
import com.example.androidparty.ResponseListener
import com.example.androidparty.database.DataRefreshRates
import com.example.androidparty.database.ServerEntity
import com.example.androidparty.database.ServersDB
import com.example.androidparty.model.AuthToken
import com.example.androidparty.model.Server
import com.example.androidparty.model.UserRequest
import com.example.androidparty.networking.AuthClient
import com.example.androidparty.networking.DataClient
import com.example.androidparty.networking.DataClientImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryImpl(private val authClient: AuthClient, val prefs: SharedPreferences, val serversDB: ServersDB) : Repository {

    val job = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + job)

    override fun getToken(username: String, password: String, listener: ResponseListener<String>) {
        authClient.sendCredentials(UserRequest(username, password), object : ResponseListener<AuthToken> {
            override fun <T> onResult(data: T) {
                if (data is AuthToken) {
                    listener.onResult(data.token)
                    prefs.edit().putString("token", data.token).apply()
                }
            }
        })
    }


    override fun getServersList(listener: ResponseListener<List<Server>>) {
        val token = prefs.getString("token", "token")
        val mDataClient : DataClient = DataClientImpl(token!!)
        val lastRefreshTime = prefs.getLong(DataRefreshRates.Server.ID, 0)
        if (DataRefreshRates.Server.renewTimeMS <= System.currentTimeMillis() - lastRefreshTime) {
            mDataClient.getServersList(object : ResponseListener<List<Server>>{
                override fun <T> onResult(data: T) {
                    if (data != null){
                        val servers = data as List<Server>
                        listener.onResult(servers)
                        prefs.edit().putLong(DataRefreshRates.Server.ID, System.currentTimeMillis()).apply()
                        ioScope.launch {
                            val serversEntity = servers.map {server -> ServerEntity(server.name, server.distance) }
                            for(server in serversEntity)
                            serversDB.serverDao().updateServer(server)
                        }
                    }
                }
            })
        } else {
            listener.onResult(serversDB.serverDao().getServers().map { serverEntity -> Server(serverEntity.serverName, serverEntity.distance) })
        }
    }

    companion object {
        private var instance: RepositoryImpl? = null

        fun getInstance(authClient: AuthClient, prefs: SharedPreferences, serversDB: ServersDB): RepositoryImpl {
            if (instance == null) {
                instance = RepositoryImpl(authClient, prefs, serversDB)
            }
            return instance!!
        }
    }
}