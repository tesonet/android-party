package com.edvinas.balkaitis.party.servers.mvp

import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.utils.mvp.BasePresenter

interface ServersContract {
    interface View {
        fun populateServers(servers: Array<Server>)
        fun setList()
        fun showLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun onCreated(servers: Array<Server>?)
        fun onLogoutClicked()
    }
}
