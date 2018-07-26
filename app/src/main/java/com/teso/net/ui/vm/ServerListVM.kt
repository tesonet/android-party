package com.teso.net.ui.vm

import android.arch.lifecycle.LiveData
import com.teso.net.AndroidApplication
import com.teso.net.data_flow.database.entities.ServerEntity
import com.teso.net.data_flow.interactions.IServerInteractor
import com.teso.net.data_flow.interactions.ITokenInteractor
import com.teso.net.ui.base.BaseViewModel
import javax.inject.Inject

class ServerListVM : BaseViewModel() {

    @Inject lateinit var serverInteractor: IServerInteractor

    @Inject lateinit var tokenInteractor: ITokenInteractor

    init {
        AndroidApplication.component.inject(this)
    }

    fun getListOfServers(): LiveData<List<ServerEntity>> = serverInteractor.getListOfServers()

    fun logout() {
        tokenInteractor.clearPassword()
        tokenInteractor.clearUserName()
        tokenInteractor.clearToken()
        serverInteractor.clearServerList()
    }
}