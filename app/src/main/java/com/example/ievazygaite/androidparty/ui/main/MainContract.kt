package com.example.ievazygaite.androidparty.ui.main

import com.example.ievazygaite.androidparty.data.server.Server
import com.example.ievazygaite.androidparty.ui.base.BaseContract

class MainContract {
    interface View : BaseContract.View {
        fun showListFragment(servers:List<Server>)
        fun showLoginFragment()
    }
    interface Presenter : BaseContract.Presenter<MainContract.View>
}