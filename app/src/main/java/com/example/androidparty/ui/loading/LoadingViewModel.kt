package com.example.androidparty.ui.loading

import android.annotation.SuppressLint
import android.app.Application
import com.example.androidparty.dao.ServerDao
import com.example.androidparty.db.ServerEntity
import com.example.androidparty.repository.ServerRepository
import com.example.androidparty.ui.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoadingViewModel(application: Application) : BaseViewModel(application) {

    private var serversObservable: Observable<List<ServerEntity>> = Observable.empty()
    private val serverDao: ServerDao = databaseInstance.serverDao()
    private val repository: ServerRepository

    init {
        repository = ServerRepository(serverDao)
    }

    @SuppressLint("CheckResult")
    fun getServers(token: String, connectedToInternet: Boolean) {
        serversObservable = if (connectedToInternet) {
            repository.getServersList(token)
                .doOnNext { servers -> insertDataToDB(servers) }
        } else {
            repository.allServers
        }
    }

    private fun insertDataToDB(dataServers: List<ServerEntity>) {
        repository.removeServersFromDB()
            .andThen(repository.insertServersList(dataServers))
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getServersObservable(): Observable<List<ServerEntity>> {
        return serversObservable
    }
}