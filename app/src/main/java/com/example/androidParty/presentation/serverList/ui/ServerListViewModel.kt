package com.example.androidParty.presentation.serverList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidParty.authentication.GetTokenUseCase
import com.example.androidParty.authentication.LogoutUseCase
import com.example.androidParty.datalayer.network.Resource
import com.example.androidParty.presentation.serverList.domain.entity.Server
import com.example.androidParty.presentation.serverList.domain.usecase.GetServerListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val getServerList: GetServerListUseCase,
    private val getToken: GetTokenUseCase,
    private val logout: LogoutUseCase
) : ViewModel() {
    private val listOfServer: MutableLiveData<Resource<List<Server>>> = MutableLiveData()
    val listServer: LiveData<Resource<List<Server>>>
        get() = listOfServer

    init {
        loadAllServers()
    }

    private fun loadAllServers() {
        viewModelScope.launch {
            getToken().collect { token ->
                token?.let { it ->
                    getServerList(it).onEach { server ->
                        when (server) {
                            is Resource.Error -> listOfServer.value =
                                Resource.Error("An Error Happened")
                            is Resource.Loading -> listOfServer.value = Resource.Loading()
                            is Resource.Success -> listOfServer.value = server
                        }
                    }.launchIn(viewModelScope)
                }
            }

        }
    }

    fun logoutBtn() {
        viewModelScope.launch {
            logout()
        }
    }
}