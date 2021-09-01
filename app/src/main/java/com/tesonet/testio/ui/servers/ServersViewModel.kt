package com.tesonet.testio.ui.servers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tesonet.testio.managers.ServersManager
import com.tesonet.testio.services.data.server.Server
import com.tesonet.testio.utils.Resource

class ServersViewModel(private val serversManager: ServersManager) : ViewModel() {

    val servers: MutableLiveData<Resource<List<Server>>>
        get() = serversManager.servers

    init {
        getServers()
    }

    private fun getServers() {
        serversManager.getServersFromDatabase()
    }

    fun deleteAllServers() {
        serversManager.deleteAllServersFromDatabase()
    }
}