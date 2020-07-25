package gj.tesonet.ui.servers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gj.tesonet.data.model.Server
import gj.tesonet.data.model.User
import gj.tesonet.ui.AppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class ServerListViewModel(application: Application): AppViewModel(application) {

    private val _servers = MutableLiveData<List<Server>>()

    val servers: LiveData<List<Server>>
        get() = _servers

    init {
        load()
    }

    fun logout() {
        app.user = app.user?.let {
            User(it.name, "")
        }
    }

    // public so reload can be initiated from view
    // although in that case it would make sense to terminate running job, if any
    fun load() {
        _servers.value = null

        viewModelScope.launch {
            _servers.value = loadRemote()?.also { list ->
                // run async
                launch(Dispatchers.IO) {
                    // making an assumption, that list is not big
                    // so we can replace all local records
                    data.servers().setAll(list)
                }
            } ?: withContext(Dispatchers.IO) {
                data.servers().getAll()
            }
        }
    }

    private suspend fun loadRemote(): List<Server>? {
        if (!app.online) return null

        val user = app.user ?: return null

        return withContext(Dispatchers.IO) {
            try {
                // token might be saved after login into App
                // but most likely it is short-lived so we login again
                val token = backend.login(user)
                backend.getServers(token.bearer)
            } catch (e: Exception) {
                Timber.e(e, "servers load failed")
                null
            }
        }
    }
}
