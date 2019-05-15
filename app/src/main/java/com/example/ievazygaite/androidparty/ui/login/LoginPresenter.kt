package com.example.ievazygaite.androidparty.ui.login

import com.example.ievazygaite.androidparty.data.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter : LoginContract.Presenter {
    private lateinit var view: LoginContract.View

    override fun authorize(username: String, password: String) {

        val subscribe = DataManager.auth(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                DataManager.prefs.addToken(it.token)
                view.showProgress(true)
                loadServers(it.token)
            }, {
                view.showErrorMessage(it.localizedMessage)
            })
        subscriptions.add(subscribe)

    }

    fun loadServers(token: String) {
        val subscribe = DataManager.getServers(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.showProgress(false)
                    view.showListFragment(it)
                },
                {
                    view.showProgress(false)
                    view.showErrorMessage(it.message!!)
                }
            )
        subscriptions.add(subscribe)
    }

    override fun attach(view: LoginContract.View) {
        this.view = view
    }

    private val subscriptions = CompositeDisposable()

    override fun unsubscribe() {
        subscriptions.clear()
    }
}