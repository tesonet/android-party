package com.tesonet.testio.services.repositories

import android.util.Log
import com.tesonet.testio.services.client.ApiClient
import com.tesonet.testio.services.data.server.Server
import com.tesonet.testio.services.data.token.Token
import com.tesonet.testio.services.data.user.RequestUser
import com.tesonet.testio.services.database.ServersDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.SECONDS

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
            .delay(3, SECONDS) // for demo purpose
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { saved(true) },
                { saved(false) }
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

    fun deleteAllServersFromDatabase(): Single<Unit> {
        return Single.fromCallable {
            serversDao.deleteAllServers()
        }
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