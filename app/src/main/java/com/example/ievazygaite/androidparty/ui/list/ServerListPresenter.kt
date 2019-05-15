package com.example.ievazygaite.androidparty.ui.list

import com.example.ievazygaite.androidparty.data.DataManager
import com.example.ievazygaite.androidparty.data.server.Server
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ServerListPresenter : ServerListContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: ServerListContract.View

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ServerListContract.View) {
        this.view = view
    }

    override fun getServerList(servers: Array<Server>?) {
        if (servers == null) {
            val subscribe = DataManager.getServers(DataManager.prefs.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view.showList(it)
                    },
                    {
                        view.showErrorMessage(it.message!!)
                    }
                )
            subscriptions.add(subscribe)
        } else {
            view.showList(servers.toList())
        }
    }

    override fun logout() {
        DataManager.prefs.clear()
        view.showLoginFragment()
    }

}