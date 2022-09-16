package com.ac.androidparty.servers.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.androidparty.servers.data.repository.ServersListRepository
import com.ac.androidparty.servers.data.repository.ServersResult
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

    private val _state = MutableStateFlow(ServersListState.Loading)
    val state: StateFlow<ServersListState>
        get() = _state

    init {
        getServers()
    }

    fun getServers() {
        viewModelScope.launch {
            flowOf(
                serversListRepository.getServers()
            ).collect(::applyServersResult)
        }
    }

    private fun applyServersResult(serversResult: ServersResult) {
        Log.d("lmao", serversResult::class.toString())
    }

}