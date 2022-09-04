package com.yasserakbbach.androidparty.listsevers.presenation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasserakbbach.androidparty.listsevers.domain.usecase.GetAllServersUseCase
import com.yasserakbbach.androidparty.navigation.UiEvent
import com.yasserakbbach.androidparty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val getAllServersUseCase: GetAllServersUseCase,
) : ViewModel() {

    var state by mutableStateOf(ServerListState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val event = _uiEvent.receiveAsFlow()

    init {
        loadServers()
    }

    fun onEvent(event: ServerListEvent) {
        when(event) {
            ServerListEvent.OnLogoutClick -> onLogoutClick()
            ServerListEvent.OnSwipeRefresh -> onSwipeRefresh()
        }
    }

    private fun loadServers() {
        viewModelScope.launch {
            getAllServersUseCase().collectLatest { data ->
                when(data) {
                    is Resource.Error -> {
                        Log.d("LOAD_SERVER", "loadServers: error:${data.error}")
                        state = state.copy(
                            isLoading = false,
                            isRefreshing = false,
                        )
                    }
                    is Resource.Loading -> {
                        Log.d("LOAD_SERVER", "loadServers: #loading...")
                        state = state.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        Log.d("LOAD_SERVER", "loadServers: data:${data.data}")
                        state = state.copy(
                            servers = data.data ?: emptyList(),
                            isLoading = false,
                            isRefreshing = false,
                        )
                    }
                }
            }
        }
    }

    private fun onLogoutClick() {

    }

    private fun onSwipeRefresh() {
        state = state.copy(
            isRefreshing = true,
            isLoading = false,
        )
        loadServers()
    }
}