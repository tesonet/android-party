package com.example.feature_server.presentation.viewmodel

import com.example.core.viewmodel.ViewEffect
import com.example.core.viewmodel.ViewEvent
import com.example.core.viewmodel.ViewState
import com.example.feature_server.presentation.model.ServerUiModel

object ServerContract {
    data class State(
        val serverList: List<ServerUiModel> = emptyList()
    ) : ViewState

    sealed class Event : ViewEvent {
        object OnLoadingOpened : Event()
        object OnLogoutClicked : Event()
    }

    sealed class Effect : ViewEffect {
        object NetworkErrorEffect : Effect()
        data class UnknownErrorEffect(val message: String) : Effect()
        object OnNavigationEffect : Effect()
        object OnLogoutEffect : Effect()
    }
}
