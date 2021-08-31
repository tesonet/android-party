package com.tesonet.testio.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tesonet.testio.managers.ServersManager

class LoadingViewModel(private val serversManager: ServersManager) : ViewModel() {

    val savedServers: LiveData<Boolean>
        get() = serversManager.savedServers

    fun fetchServers(token: String) {
        serversManager.fetchServers(token)
    }

    fun getServers() {
        serversManager.getServersFromDatabase()
    }
}