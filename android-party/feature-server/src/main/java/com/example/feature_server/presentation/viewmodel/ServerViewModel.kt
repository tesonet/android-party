package com.example.feature_server.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.BaseViewModel
import com.example.domain_server.domain.usecase.FetchServerUseCase
import com.example.feature_server.presentation.mapper.DomainToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerViewModel @Inject constructor(
    private val fetchServer: FetchServerUseCase
) : BaseViewModel<ServerContract.Event, ServerContract.State, ServerContract.Effect>() {
    private val mapper = DomainToUiMapper()

    override fun provideInitialState(): ServerContract.State {
        return ServerContract.State()
    }

    private fun fetchServers() {
        viewModelScope.launch {
            fetchServer.execute(FetchServerUseCase.Input).collect { output ->
                when (output) {
                    is FetchServerUseCase.Output.Success -> {
                        val serverList = output.servers.map {
                            mapper.map(it)
                        }
                        updateState { copy(serverList = serverList) }
                        sendEffect { ServerContract.Effect.OnNavigationEffect }
                    }
                    is FetchServerUseCase.Output.NetworkError -> {
                        sendEffect { ServerContract.Effect.NetworkErrorEffect }
                    }
                    is FetchServerUseCase.Output.UnknownError -> {
                        sendEffect { ServerContract.Effect.UnknownErrorEffect(message = output.message) }
                    }
                }
            }
        }
    }

    override fun handleEvent(event: ServerContract.Event) {
        when (event) {
            is ServerContract.Event.OnLoadingOpened -> {
                fetchServers()
            }

            is ServerContract.Event.OnLogoutClicked -> {
                sendEffect { ServerContract.Effect.OnLogoutEffect }
            }
        }
    }

}