package com.example.androidparty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidparty.ResponseListener
import com.example.androidparty.model.Server
import com.example.androidparty.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ServersListViewModel(val repository: Repository) : ViewModel() {
    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val mServersList = MutableLiveData<List<ServerViewModel>>()

    fun getServersList() {
        ioScope.launch {
            repository.getServersList(object : ResponseListener<List<Server>> {
                override fun <T> onResult(data: T) {
                    data as List<Server>
                    val serverVM = data.map { server -> ServerViewModel(server.name, server.distance) }
                    uiScope.launch {
                        mServersList.value = serverVM
                    }
                }
            })
        }
    }
}