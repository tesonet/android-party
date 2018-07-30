package com.alex.tesonettesttask.ui.presenters

import com.alex.tesonettesttask.logic.entities.Server
import com.alex.tesonettesttask.logic.model.LoginModel
import com.alex.tesonettesttask.logic.model.ServersModel
import javax.inject.Inject

class ServerPresenter @Inject constructor(private val loginModel: LoginModel,
                                          private val serversModel: ServersModel) {

    fun logout() {
        loginModel.removeToken()
        serversModel.deleteServers()
    }

    fun getCachedServers(): List<Server> {
        return serversModel.getCachedServers()
    }

}

