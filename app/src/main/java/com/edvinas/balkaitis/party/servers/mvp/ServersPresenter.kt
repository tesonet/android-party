package com.edvinas.balkaitis.party.servers.mvp

import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.utils.mvp.ViewPresenter

class ServersPresenter(
        val tokenStorage: TokenStorage
) : ServersContract.Presenter, ViewPresenter<ServersContract.View>() {
    override fun onLogoutClicked() {
        tokenStorage.removeToken()
        onView { showLogin() }
    }

    override fun onCreated(servers: Array<Server>?) {
        onView { setList() }
        servers?.let { nonNullServers ->
            onView { populateServers(nonNullServers) }
        }
    }
}
