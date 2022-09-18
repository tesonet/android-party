package com.ac.androidparty.servers.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.androidparty.login.domain.usecase.LogoutUseCase
import com.ac.androidparty.servers.data.repository.serverslist.asServersListState
import com.ac.androidparty.servers.domain.usecase.GetServersUseCase
import com.ac.androidparty.servers.presentation.ServersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ServersListViewModel @Inject constructor(
    private val serversUseCase: GetServersUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ServersListState>(ServersListState.Loading)
    val state: StateFlow<ServersListState>
        get() = _state

    init {
        getServers()
    }

    val isLoggedIn: MutableState<Boolean> = mutableStateOf(true)

    private fun getServers() {
        viewModelScope.launch {
            flowOf(
                serversUseCase.getServers().asServersListState()
            ).collect(::applyServersResult)
        }
    }

    fun refreshServers() = viewModelScope.launch {
        _state.value = ServersListState.Loading
        flowOf(serversUseCase.getServers().asServersListState()).collect(::applyServersResult)
    }

    fun logout() {
        isLoggedIn.value = logoutUseCase.logout()
    }

    private fun applyServersResult(serversListState: ServersListState) {
        _state.value = serversListState
    }
}