package com.edvinas.balkaitis.party.login.mvp

import com.edvinas.balkaitis.party.login.network.LoginBody
import com.edvinas.balkaitis.party.login.network.LoginService
import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.servers.network.ServersService
import com.edvinas.balkaitis.party.utils.mvp.ViewPresenter
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class LoginPresenter(
    private val mainScheduler: Scheduler,
    private val loginService: LoginService,
    private val tokenStorage: TokenStorage,
    private val serversService: ServersService
) : LoginContract.Presenter, ViewPresenter<LoginContract.View>() {

    override fun onLoginClicked(username: String, password: String) {
        loginService.login(LoginBody(username, password))
            .observeOn(mainScheduler)
            .doOnSuccess { response ->
                tokenStorage.saveToken(response.token)
                onView { showLoadingView() }
            }
            .flatMap { serversService.getServers() }
            .subscribe(::onServersDownloaded, ::onError)
            .addTo(subscription)
    }

    private fun onServersDownloaded(servers: List<Server>) {
        onView { showServers(servers) }
    }

    private fun onError(throwable: Throwable) {
        onView { showError(throwable.localizedMessage) }
        onView { hideLoadingView() }
        Timber.e(throwable)
    }
}
