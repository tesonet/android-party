package com.example.androidparty.repository

import android.annotation.SuppressLint
import com.example.androidparty.dao.ServerDao
import com.example.androidparty.db.ServerEntity
import com.example.androidparty.network.Network
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ServerRepository(private val serverDao: ServerDao) {

    val allServers: Observable<List<ServerEntity>> = serverDao.getAll()

    @SuppressLint("CheckResult")
    fun getServersList(token: String): Observable<List<ServerEntity>> {
        return Network().getApi().getServers(token)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertServersList(server: List<ServerEntity>): Completable {
        return Completable.fromAction { serverDao.insertAll(server) }
            .doOnError { t: Throwable? ->
                Timber.e(t)
            }
    }

    fun removeServersFromDB(): Completable {
        return Completable.fromAction(serverDao::deleteAllServers)
            .doOnError { t: Throwable? ->
                Timber.e(t)
            }
    }
}