package com.tesonet.testio.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tesonet.testio.managers.ServersManager
import com.tesonet.testio.utils.Resource

class LoadingViewModel(private val serversManager: ServersManager) : ViewModel() {

    val savedServers: LiveData<Resource<Boolean>>
        get() = serversManager.savedServersState

    fun fetchServers(token: String) {
        serversManager.fetchServers(token)
    }

    fun resetSavedServerState() {
        serversManager.resetServers()
    }
}