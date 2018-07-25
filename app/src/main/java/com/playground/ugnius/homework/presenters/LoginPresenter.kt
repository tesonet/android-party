package com.playground.ugnius.homework.presenters

import android.content.SharedPreferences
import com.playground.ugnius.homework.interfaces.LoginView
import com.playground.ugnius.homework.model.clients.ApiClient
import com.playground.ugnius.homework.model.entities.UserRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
class LoginPresenter constructor(
    private val loginView: LoginView,
    private val apiClient: ApiClient,
    private val preferences: SharedPreferences
) {

    private val compositeDisposable = CompositeDisposable()

    fun requestToken(userRequest: UserRequest) {
        val disposable = apiClient.requestToken(userRequest)
            .map { preferences.edit().putString("token", it.token).apply() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { loginView.goToServersFragment() }
                ,
                onError = { loginView.showError() }
            )
        compositeDisposable.add(disposable)

    }

    fun clear() = compositeDisposable.clear()
}