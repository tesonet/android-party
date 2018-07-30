package com.alex.tesonettesttask.ui.presenters

import com.alex.tesonettesttask.logic.entities.Server
import com.alex.tesonettesttask.logic.model.LoginModel
import com.alex.tesonettesttask.logic.model.ServersModel
import com.alex.tesonettesttask.logic.network.NetworkCallbackWrapper
import com.alex.tesonettesttask.logic.network.request.LoginRequest
import com.alex.tesonettesttask.logic.network.response.AccessTokenResponse
import com.alex.tesonettesttask.ui.interfaces.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter @Inject constructor(
        private val loginModel: LoginModel,
        private val serversModel: ServersModel) {

    lateinit var loginView: LoginView

    fun login(userRequest: LoginRequest) {
        loginModel.login(userRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : NetworkCallbackWrapper<AccessTokenResponse>() {
                    override fun onSuccess(body: AccessTokenResponse) {
                        loginModel.saveToken(body.token)
                        loginView.onLogin()
                    }

                    override fun onFail(message: String) {
                        loginView.onError(message)
                    }
                })
    }

    fun fetchServers() {
        serversModel.fetchServers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : NetworkCallbackWrapper<List<Server>>() {
                    override fun onSuccess(body: List<Server>) {
                        serversModel.saveServers(body)
                        loginView.onServersFetched()
                    }

                    override fun onFail(message: String) {
                        loginModel.removeToken()
                        loginView.onError(message)
                    }
                })
    }


}

