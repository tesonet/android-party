package gj.tesonet.ui.servers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gj.tesonet.data.model.Server
import gj.tesonet.ui.AppViewModel
import kotlinx.coroutines.*
import timber.log.Timber

class ServerListViewModel(application: Application): AppViewModel(application) {

    private val _servers = MutableLiveData<List<Server>>()

    val servers: LiveData<List<Server>>
        get() = _servers

    init {
        load()
    }

    private fun load() {
        _servers.value = null

        viewModelScope.launch {
            _servers.value = loadRemote()?.also {
                launch {
                    saveLocal(it)
                }
            } ?: loadLocal()
        }
    }

    private suspend fun loadRemote(): List<Server>? {
        if (!app.online) return null

        val user = app.user ?: return null

        return withContext(Dispatchers.IO) {
            try {
                // token might be saved after login into App
                // but most likely it is short-lived so we login again
                val token = app.backend.login(user)
                app.backend.getServers(token.bearer)
            } catch (e: Exception) {
                Timber.e(e, "servers load failed")
                null
            }
        }
    }

    private suspend fun loadLocal(): List<Server> {
        return withContext(Dispatchers.IO) {
            app.data.servers().getAll()
        }
    }

    private suspend fun saveLocal(list: List<Server>) {
        withContext(Dispatchers.IO) {
            app.data.servers().setAll(list)
        }
    }
}
