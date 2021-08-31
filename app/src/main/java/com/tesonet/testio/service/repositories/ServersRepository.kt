package com.tesonet.testio.service.repositories

import android.util.Log
import com.tesonet.testio.service.client.ApiClient
import com.tesonet.testio.service.data.token.Token
import com.tesonet.testio.service.data.user.RequestUser
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.SECONDS

class ServersRepository(private val apiClient: ApiClient) {

    fun getTokenAccess(requestUser: RequestUser): Observable<Token> {
        return apiClient.requestUserToken(requestUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e(TAG, "Token access error: ${error.localizedMessage}", error)
            }
    }

    fun getServerList() {
    }

    companion object {
        val TAG: String = ServersRepository::class.java.simpleName
        const val UNKNOWN_ERROR: String = "Unknown error"
        const val ERROR_HTTP_401: String = "HTTP 401 "
    }
}