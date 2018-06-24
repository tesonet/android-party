package com.ne2rnas.party.ui.login

import com.ne2rnas.party.base.BasePresenter
import com.ne2rnas.party.data.login.LoginResponse
import com.ne2rnas.party.network.TesonetApi
import com.ne2rnas.party.utils.SharedPrefsHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter(loginView: LoginView) : BasePresenter<LoginView>(loginView) {
    @Inject
    lateinit var tesonetApi: TesonetApi
    @Inject
    lateinit var sharedPrefs: SharedPrefsHelper
    private var subscription: Disposable? = null

    override fun onViewCreated() {
        if (sharedPrefs.isUserLoggedIn() && !sharedPrefs.getToken().isNullOrEmpty()) {
            view.openServersActivity()
        }
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }

    fun onServerLoginClick(email: String, password: String) {
        subscription = tesonetApi.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onLoginSuccessful(result) },
                        { error -> onLoginError(error.localizedMessage) }
                )
    }

    private fun onLoginSuccessful(loginResponse: LoginResponse) {
        view.hideLoading()
        sharedPrefs.setUserLoggedIn(true)
        sharedPrefs.setToken(loginResponse.token)
        view.openServersActivity()
    }

    private fun onLoginError(error: String?) {
        view.hideLoading()
        view.showError(error)
    }
}
