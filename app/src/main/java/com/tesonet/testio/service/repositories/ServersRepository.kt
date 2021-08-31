package com.tesonet.testio.service.repositories

import android.util.Log
import com.tesonet.testio.service.client.ApiClient
import com.tesonet.testio.service.data.server.Server
import com.tesonet.testio.service.data.token.Token
import com.tesonet.testio.service.data.user.RequestUser
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ServersRepository(private val apiClient: ApiClient) {

    fun getTokenAccess(requestUser: RequestUser): Single<Token> {
        return apiClient.requestUserToken(requestUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e(TAG, "Token access error: ${error.localizedMessage}", error)
            }
    }

    fun getServerList(requestToken: String): Single<List<Server>> {
        return apiClient.fetchServers(buildTokenWithAuth(requestToken))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e(TAG, "Fetch server error: ${error.localizedMessage}", error)
            }
    }

    private fun buildTokenWithAuth(token: String): String {
        return "Bearer $token"
    }

    companion object {
        val TAG: String = ServersRepository::class.java.simpleName
        const val UNKNOWN_ERROR: String = "Unknown error"
        const val ERROR_HTTP_401: String = "HTTP 401 "
    }
}