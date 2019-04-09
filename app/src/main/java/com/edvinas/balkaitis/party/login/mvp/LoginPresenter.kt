package com.edvinas.balkaitis.party.login.mvp

import com.edvinas.balkaitis.party.login.network.LoginBody
import com.edvinas.balkaitis.party.login.network.LoginResponse
import com.edvinas.balkaitis.party.login.network.LoginService
import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.utils.mvp.ViewPresenter
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class LoginPresenter(
        private val mainScheduler: Scheduler,
        private val loginService: LoginService,
        private val tokenStorage: TokenStorage,
        private val networkScheduler: Scheduler
) : LoginContract.Presenter, ViewPresenter<LoginContract.View>() {
    override fun onLoginClicked(username: String, password: String) {
        loginService.login(LoginBody(username, password))
                .subscribeOn(networkScheduler)
                .observeOn(mainScheduler)
                .doOnSubscribe { onView { showLoadingView() } }
                .subscribe(::onLoginComplete, ::onLoginError)
                .addTo(subscription)
    }

    private fun onLoginComplete(response: LoginResponse) {
        tokenStorage.saveToken(response.token)
    }

    private fun onLoginError(throwable: Throwable) {
        onView { showError(throwable.localizedMessage) }
        Timber.e(throwable)
    }
}
