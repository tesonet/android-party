package com.giedrius.androidparty.task.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.giedrius.androidparty.task.utils.ApiListener
import com.giedrius.androidparty.task.data.Repository
import com.giedrius.androidparty.task.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ServersListViewModel(val repository: Repository) : ViewModel() {
    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    val servers = MutableLiveData<List<ServerViewModel>>()

    fun getServers() {
        ioScope.launch {
            repository.getServers(object :
                ApiListener<List<Server>> {
                override fun <T> onResult(data: T) {
                    data as List<Server>
                    val server: List<ServerViewModel> = data.map { ServerViewModel(it.name, it.distance, Constants.SERVER_DISTANCE_UNIT) }
                    uiScope.launch { servers.value = server }
                }
            })
        }
    }
}