package com.playground.ugnius.homework.presenters

import com.playground.ugnius.homework.interfaces.LoginView
import com.playground.ugnius.homework.model.ServersRepository
import com.playground.ugnius.homework.model.clients.ApiClient
import com.playground.ugnius.homework.model.entities.UserRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit.SECONDS

class LoginPresenter constructor(
    private val loginView: LoginView,
    private val apiClient: ApiClient,
    private val serversRepository: ServersRepository
) {

    fun login(userRequest: UserRequest) {
        apiClient.requestToken(userRequest)
            .flatMap { apiClient.getServers("Bearer ${it.token}") }
            .observeOn(AndroidSchedulers.mainThread())
            .map { serversRepository.saveServers(it) }
            .delay(1, SECONDS)
            .subscribeBy(
                onSuccess = {
                    loginView.goToServersFragment()
                },
                onError = {
                    loginView.showError()
                }
            )
    }
}