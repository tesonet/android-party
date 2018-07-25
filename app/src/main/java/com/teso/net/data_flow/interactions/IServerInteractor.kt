package com.teso.net.data_flow.interactions

import android.arch.lifecycle.LiveData
import com.teso.net.data_flow.database.entities.ServerEntity
import io.reactivex.Single

interface IServerInteractor {
    fun getListOfSites(): LiveData<List<ServerEntity>>
    fun updateListOfServers(): Single<List<ServerEntity>>
    fun writeServersToDb(sitesList: List<ServerEntity>)
}