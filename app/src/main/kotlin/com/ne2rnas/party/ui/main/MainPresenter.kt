package com.ne2rnas.party.ui.main

import com.ne2rnas.party.base.BasePresenter
import com.ne2rnas.party.utils.SharedPrefsHelper
import javax.inject.Inject

class MainPresenter(mainView: MainView) : BasePresenter<MainView>(mainView) {
    @Inject
    lateinit var sharedPrefs: SharedPrefsHelper

    override fun onViewCreated() {
        if (sharedPrefs.isUserLoggedIn() && !sharedPrefs.getToken().isNullOrEmpty()) {
            view.openServersActivity()
        } else {
            view.openLoginActivity()
        }
    }
}
