package com.example.justinaszableckisand.androidparty.Servers

import com.example.justinaszableckisand.androidparty.Base.BaseView
import com.example.justinaszableckisand.androidparty.Models.Server

interface ServersContract {
    interface View : BaseView<Presenter>{
        fun onSuccess(serversList : List<Server>)
    }
    interface Presenter {
        fun getServers(token : String)
    }
}