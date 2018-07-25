package com.teso.net.data_flow.interactions

import android.arch.lifecycle.LiveData
import com.teso.net.data_flow.converters.ServerConverter
import com.teso.net.data_flow.database.LocalDatabase
import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.data_flow.network.Api
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber

class ServerInteractor(private val api: Api, private val db: LocalDatabase) : IServerInteractor {

    override fun writeServersToDb(sitesList: List<ServerEntity>) {
        Timber.d("Write new server list to DB ${sitesList.size}")
        db.serverDao().updateServer(*sitesList.toTypedArray())
    }

    override fun updateListOfServers(): Single<List<ServerEntity>> {
        return api.getSitesList()
                .flatMap { Flowable.fromIterable(it) }
                .map { ServerConverter.convert(it) }
                .toList()
    }

    override fun getListOfSites(): LiveData<List<ServerEntity>> {
        return db.serverDao().getServerList()
    }
}