package com.demo.androidparty.ui.list

import androidx.lifecycle.viewModelScope
import com.demo.androidparty.base.BaseViewModel
import com.demo.androidparty.dto.ServerModel
import com.demo.androidparty.ui.list.mapper.ServerListMapper
import com.demo.androidparty.utils.InternetStateProvider
import com.demo.androidparty.utils.NetworkResult
import kotlinx.coroutines.launch

class ServersListViewModel(
    private val model: ServerListModel,
    private val mapper: ServerListMapper,
    private val internetStateProvider: InternetStateProvider
) : BaseViewModel() {

    internal val state = createMutableLiveData<ServerListFetchingState>()
    internal val logout = createSingleLiveEvent<Unit>()

    init {
        fetchServersList()
    }

    internal fun logout() = viewModelScope.launch {
        model.clearData()
        logout.call()
    }

    private fun fetchServersList() = viewModelScope.launch {
        if (internetStateProvider.isOnline()) {
            fetchRemoteServerList()
        } else {
            fetchLocalServerList()
        }
    }

    private suspend fun fetchRemoteServerList() {
        state.setValue(ServerListFetchingState.Started)
        when (val result = model.fetchRemoteServersData()) {
            is NetworkResult.Success -> {
                val servers = result.data
                model.saveServersData(servers)
                setServers(servers)
            }
            is NetworkResult.Error -> {
                val failedState = ServerListFetchingState.Failed(
                    "Something went wrong! Response code ${result.errorCode}"
                )
                state.setValue(failedState)
            }
        }
    }

    private suspend fun fetchLocalServerList() {
        state.setValue(ServerListFetchingState.Started)
        val servers = model.fetchDatabaseServersData()
        setServers(servers)
    }

    private fun setServers(servers: List<ServerModel>) {
        val list = servers.map { mapper.map(it) }
        val successState = ServerListFetchingState.Success(list)
        state.setValue(successState)
    }
}