package com.assignment.android_party2.servers.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assignment.android_party2.servers.models.ServerModel
import com.assignment.android_party2.servers.repo.ServersRepository

class ServersViewModel : ViewModel() {

    var serversCallback: ServersCallback? = null
    var serversRepository: ServersRepository = ServersRepository()

    fun getServersFromDb(): LiveData<List<ServerModel>>
    {
        return serversRepository.getServersFromDb()
    }

    fun getServersFromApiAndStore(authToken: String?)
    {
        serversRepository.getServersFromApiAndUpdateDb(authToken.toString())
    }
}