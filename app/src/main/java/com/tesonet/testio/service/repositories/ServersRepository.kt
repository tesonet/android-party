package com.tesonet.testio.service.repositories

import android.util.Log
import com.tesonet.testio.service.client.ApiClient
import com.tesonet.testio.service.data.server.Server
import com.tesonet.testio.service.data.token.Token
import com.tesonet.testio.service.data.user.RequestUser
import com.tesonet.testio.service.database.ServersDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ServersRepository(private val apiClient: ApiClient, private val serversDao: ServersDao) {

    private val _compositeDisposable = CompositeDisposable()

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

    fun saveServersToDatabase(servers: List<Server>, saved: ((Boolean) -> Unit)) {
        Single.fromCallable {
            serversDao.addAllServers(servers)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { success ->
                    success
                    saved(true)
                },
                { error ->
                    error
                    saved(false)
                }
            ).also { _compositeDisposable.addAll(it) }
    }

    fun getServersFromDatabase(): Single<List<Server>> {
        return serversDao.readAllServers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { error ->
                Log.e(TAG, "Servers from database error: ${error.localizedMessage}", error)
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