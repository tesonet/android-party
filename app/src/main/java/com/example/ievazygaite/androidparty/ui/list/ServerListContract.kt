package com.example.ievazygaite.androidparty.ui.list

import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.ui.base.BaseContract

class ServerListContract {

    interface View : BaseContract.View {
        fun showErrorMessage(error: String)
        fun showList(list: List<Server>)
        fun showLoginFragment()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getServerList(servers: Array<Server>?)
        fun logout()
    }
}