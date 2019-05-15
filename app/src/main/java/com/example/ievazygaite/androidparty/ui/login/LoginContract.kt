package com.example.ievazygaite.androidparty.ui.login

import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.ui.base.BaseContract

class LoginContract {
    interface View : BaseContract.View {
        fun showErrorMessage(error: String)
        fun showListFragment(servers:List<Server>)
        fun showProgress(isShown:Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun authorize(username:String, password:String)
    }
}