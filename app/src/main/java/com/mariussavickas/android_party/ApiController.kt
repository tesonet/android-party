package com.mariussavickas.android_party

import com.codecave.outmatch.shared.Settings
import com.codecave.outmatch.shared.server.HttpService
import com.google.gson.JsonObject
import com.mariussavickas.android_party.persistance.ServerInfo
import com.mariussavickas.android_party.persistance.User
import io.reactivex.Completable
import io.reactivex.CompletableOnSubscribe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody

object ApiController {
    fun fetchAccessToken(user: User): Single<User> {
        val json = JsonObject ()
        json.addProperty ("username", user.username)
        json.addProperty ("password", user.password)

        val body = RequestBody.create(HttpService.MEDIA_TYPE_JSON, json.toString ())
        return HttpService.post(Settings.ENDPOINT_TOKEN, body)
            .map{ credentialsJson ->
                val token = credentialsJson.asJsonObject.get("token").asString
                user.token = token
                RootApplication.appDatabase.userDao().insertUser(user)
                user
            }
    }

    fun fetchServerList(user: User): Single<List<ServerInfo>> {
        return HttpService.get(Settings.ENDPOINT_SERVERS, user.token)
            .map {serverListJson ->
                val serverInfoDao = serverListJson.asJsonArray.map {serverInfo ->
                    ServerInfo(
                        serverInfo.asJsonObject.get("name").asString,
                        serverInfo.asJsonObject.get("distance").asInt
                    )
                }

                val database = RootApplication.appDatabase.serverInfoDao()
                database.clear()
                database.insert(serverInfoDao)

                serverInfoDao
            }
    }

    fun logout(): Completable {
        return Completable.create { emitter ->
            RootApplication.appDatabase.serverInfoDao().clear()
            RootApplication.appDatabase.userDao().clear()
            emitter.onComplete()
        }
        .subscribeOn(Schedulers.io())
    }
}