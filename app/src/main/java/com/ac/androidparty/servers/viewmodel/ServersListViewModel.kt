package com.ac.androidparty.servers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.androidparty.servers.data.repository.ServersListRepository
import com.ac.androidparty.servers.data.repository.asServersListState
import com.ac.androidparty.servers.presentation.ServersListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ServersListViewModel @Inject constructor(
    private val serversListRepository: ServersListRepository
) : ViewModel() {

    private val _state = MutableStateFlow<ServersListState>(ServersListState.Loading)
    val state: StateFlow<ServersListState>
        get() = _state

    init {
        getServers()
    }

    fun getServers() {
        viewModelScope.launch {
            flowOf(
                serversListRepository.getServers().asServersListState()
            ).collect(::applyServersResult)
        }
    }

    private fun applyServersResult(serversListState: ServersListState) {
        _state.value = serversListState
    }

}