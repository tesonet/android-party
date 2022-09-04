package com.yasserakbbach.androidparty.listsevers.presenation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasserakbbach.androidparty.listsevers.domain.model.Server
import com.yasserakbbach.androidparty.listsevers.domain.usecase.GetAllServersUseCase
import com.yasserakbbach.androidparty.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val getAllServersUseCase: GetAllServersUseCase,
) : ViewModel() {

    var servers by mutableStateOf<List<Server>?>(emptyList())

    init {
        loadServers()
    }

    private fun loadServers() {
        viewModelScope.launch {
            getAllServersUseCase().collectLatest { data ->
                when(data) {
                    is Resource.Error -> {
                        Log.d("LOAD_SERVER", "loadServers: error:${data.error}")
                    }
                    is Resource.Loading -> {
                        Log.d("LOAD_SERVER", "loadServers: #loading...")
                    }
                    is Resource.Success -> {
                        servers = data.data
                        Log.d("LOAD_SERVER", "loadServers: data:${data.data}")
                    }
                }
            }
        }
    }
}