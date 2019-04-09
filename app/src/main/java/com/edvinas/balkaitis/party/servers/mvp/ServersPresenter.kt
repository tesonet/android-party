package com.edvinas.balkaitis.party.servers.mvp

import com.edvinas.balkaitis.party.login.repository.TokenStorage
import com.edvinas.balkaitis.party.servers.network.Server
import com.edvinas.balkaitis.party.servers.network.ServersService
import com.edvinas.balkaitis.party.utils.mvp.ViewPresenter
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class ServersPresenter(
        private val mainScheduler: Scheduler,
        private val tokenStorage: TokenStorage,
        private val serversService: ServersService
) : ServersContract.Presenter, ViewPresenter<ServersContract.View>() {
    override fun onLogoutClicked() {
        tokenStorage.removeToken()
        onView { showLogin() }
    }

    override fun onCreated(servers: Array<Server>?) {
        onView { setList() }
        servers?.let { nonNullServers ->
            onView { populateServers(nonNullServers) }
        } ?: serversService.getServers()
                .observeOn(mainScheduler)
                .subscribe(::onServersReceived, ::onServersDownloadFailed)
                .addTo(subscription)
    }

    private fun onServersReceived(servers: List<Server>) {
        onView { populateServers(servers.toTypedArray()) }
    }

    private fun onServersDownloadFailed(throwable: Throwable) {
        Timber.e(throwable)
        onView { showError(throwable.localizedMessage) }
    }
}
