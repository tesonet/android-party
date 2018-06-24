package com.ne2rnas.party.ui.servers

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.ne2rnas.party.base.BasePresenter
import com.ne2rnas.party.data.servers.Server
import com.ne2rnas.party.network.TesonetApi
import com.ne2rnas.party.utils.SharedPrefsHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ServersPresenter(serversView: ServersView) : BasePresenter<ServersView>(serversView) {
    @Inject
    lateinit var tesonetApi: TesonetApi
    @Inject
    lateinit var sharedPrefs: SharedPrefsHelper
    private var subscription: Disposable? = null
    private val mutableServerList: MutableLiveData<List<Server>> = MutableLiveData()

    override fun onViewCreated() {
        observeServerList()
        loadServers()
    }

    private fun observeServerList() {
        val serverListObserver = Observer<List<Server>> { servers ->
            if (servers != null) {
                view.updateServers(servers)
            }
        }
        mutableServerList.observe(view, serverListObserver)
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }

    private fun loadServers() {
        view.hideAdapter()
        view.showLoading()
        subscription = tesonetApi
                .getServers(sharedPrefs.getToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { servers ->
                            onSuccess(servers)
                        },
                        { error ->
                            onError(error.localizedMessage)
                        }
                )
    }

    private fun onSuccess(servers: List<Server>?) {
        onResponse()
        mutableServerList.value = servers
        view.showAdapter()
    }

    private fun onError(error: String?) {
        onResponse()
        view.showError(error)
    }

    private fun onResponse() {
        view.hideLoading()
        view.showAdapter()
    }

    fun resetUserCredentials() {
        sharedPrefs.setToken("")
        sharedPrefs.setUserLoggedIn(false)
        view.logout()
    }
}
