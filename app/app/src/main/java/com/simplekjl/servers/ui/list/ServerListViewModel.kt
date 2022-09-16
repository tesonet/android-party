package com.simplekjl.servers.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplekjl.domain.model.ServerDetails
import com.simplekjl.domain.repository.SessionManager
import com.simplekjl.domain.usecase.GetAllServersUseCase
import com.simplekjl.domain.utils.Result.Error
import com.simplekjl.domain.utils.Result.Success
import com.simplekjl.servers.navigation.NavTarget.Login
import com.simplekjl.servers.navigation.Navigator
import com.simplekjl.servers.ui.list.ServerListState.FetchingData
import com.simplekjl.servers.ui.list.ServerListState.LoadData
import com.simplekjl.servers.ui.list.ServerListState.Logout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ServerListViewModel(
    private val getAllServersUseCase: GetAllServersUseCase,
    private val sessionManager: SessionManager,
    private val navigator: Navigator,
) : ViewModel() {

    private val _serverListState =
        MutableStateFlow<ServerListState>(FetchingData)
    val serverListState: StateFlow<ServerListState> = _serverListState

    private val _serverList = MutableStateFlow<List<ServerDetails>>(listOf())
    val serverList: StateFlow<List<ServerDetails>> = _serverList

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        getServerList()
    }

    fun logout() {
        sessionManager.deleteAuthToken()
        _serverListState.value = Logout
        navigator.navigateTo(Login)
    }

    fun swipeRefresh() {
        _isRefreshing.value = true
        getServerList()
    }

    private fun getServerList() {
        viewModelScope.launch {
            // delay to fake the response is taking longer than expected
            delay(1000L)
            when (val serversResult = getAllServersUseCase(Unit)) {
                is Error -> {
                    _isRefreshing.value = false
                }
                is Success -> {
                    _isRefreshing.value = false
                    _serverList.value = serversResult.data
                }
            }
            _serverListState.value = LoadData
        }
    }
}

sealed class ServerListState {
    object FetchingData : ServerListState()
    object LoadData : ServerListState()
    object Error : ServerListState()
    object Logout : ServerListState()
}