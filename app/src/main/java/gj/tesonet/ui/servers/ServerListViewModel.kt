package gj.tesonet.ui.servers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import gj.tesonet.App
import gj.tesonet.backend.Backend
import gj.tesonet.data.Data
import gj.tesonet.data.model.Server
import gj.tesonet.data.model.User
import gj.tesonet.ui.AppViewModel
import gj.tesonet.ui.Message
import kotlinx.coroutines.*

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
            _servers.value = loadRemote()?.also { saveLocal(it) } ?: loadLocal()
        }
    }

    private suspend fun loadRemote(): List<Server>? {
        if (!app.online) return null

        val user = app.user ?: return null

        return withContext(Dispatchers.IO) {
            val token = app.backend.login(user)
            app.backend.getServers(token.bearer)
        }
    }

    private suspend fun loadLocal(): List<Server> {
        return withContext(Dispatchers.IO) {
            app.data.servers().getAll()
        }
    }

    private suspend fun CoroutineScope.saveLocal(list: List<Server>) {
        async(Dispatchers.IO) {
            app.data.servers().setAll(list)
        }
    }
}
