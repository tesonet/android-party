package com.teso.net.data_flow.interactions

import android.arch.lifecycle.LiveData
import com.teso.net.data_flow.converters.ServerConverter
import com.teso.net.data_flow.database.LocalDatabase
import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.data_flow.network.Api
import io.reactivex.Observable
import io.reactivex.Single
import org.jetbrains.anko.doAsync
import timber.log.Timber

class ServerInteractor(private val api: Api, private val db: LocalDatabase) : IServerInteractor {

    override fun writeServersToDb(sitesList: List<ServerEntity>) {
        Timber.d("Write new server list to DB ${sitesList.size}")
        db.serverDao().clearAndUpdateServer(sitesList)
    }

    override fun updateListOfServers(): Single<List<ServerEntity>> {
        return api.getSitesList()
                .flatMap { Observable.fromIterable(it) }
                .map { ServerConverter.convert(it) }
                .toList()
    }

    override fun getListOfServers(): LiveData<List<ServerEntity>> {
        return db.serverDao().getServerList()
    }

    override fun clearServerList() {
        doAsync { db.serverDao().deleteAllServer() }
    }
}