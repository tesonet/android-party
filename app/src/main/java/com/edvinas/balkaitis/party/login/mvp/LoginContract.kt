package com.edvinas.balkaitis.party.login.mvp

import com.edvinas.balkaitis.party.utils.mvp.BasePresenter

interface LoginContract {
    interface View {
        fun showError(message: String)
        fun showLoadingView()
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClicked(username: String, password: String)
    }
}
