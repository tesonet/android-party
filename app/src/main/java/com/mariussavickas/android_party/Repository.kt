package com.mariussavickas.android_party

import com.codecave.outmatch.shared.Settings
import com.codecave.outmatch.shared.server.ApiService
import com.google.gson.JsonObject
import com.mariussavickas.android_party.persistance.AppDatabase
import com.mariussavickas.android_party.persistance.ServerInfo
import com.mariussavickas.android_party.persistance.User
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

class Repository(apiService: ApiService, appDatabase: AppDatabase) {
    private val apiService = apiService
    private val appDatabase = appDatabase

    fun fetchAccessToken(user: User): Single<User> {
        val json = JsonObject ()
        json.addProperty ("username", user.username)
        json.addProperty ("password", user.password)

        val body = RequestBody.create(ApiService.MEDIA_TYPE_JSON, json.toString ())
        return apiService.post(Settings.ENDPOINT_TOKEN, body)
            .map{ credentialsJson ->
                val token = credentialsJson.asJsonObject.get("token").asString
                user.token = token
                appDatabase.userDao().insertUser(user)
                user
            }
    }

    fun fetchServerList(user: User): Single<List<ServerInfo>> {
        return apiService.get(Settings.ENDPOINT_SERVERS, user.token)
            .map {serverListJson ->
                val serverInfoDao = serverListJson.asJsonArray.map {serverInfo ->
                    ServerInfo(
                        serverInfo.asJsonObject.get("name").asString,
                        serverInfo.asJsonObject.get("distance").asInt
                    )
                }

                val database = appDatabase.serverInfoDao()
                database.clear()
                database.insert(serverInfoDao)

                serverInfoDao
            }
    }

    fun logout(): Completable {
        return Completable.create { emitter ->
            appDatabase.serverInfoDao().clear()
            appDatabase.userDao().clear()
            emitter.onComplete()
        }
        .subscribeOn(Schedulers.io())
    }
}