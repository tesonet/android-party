package com.example.feature_login.presentation.viewmodel

import androidx.annotation.StringRes
import com.example.core.viewmodel.ViewEffect
import com.example.core.viewmodel.ViewEvent
import com.example.core.viewmodel.ViewState

object LoginContract {
    data class State(
        val isLoading: Boolean = false,
        val userName: String = "",
        val password: String = "",
    ) : ViewState

    sealed class Event : ViewEvent {
        object OnLoginSuccess : Event()
        data class OnLoginClicked(
            val userName: String,
            val password: String
        ) : Event()
    }

    sealed class Effect : ViewEffect {
        object NetworkErrorEffect : Effect()
        data class UnknownErrorEffect(val message: String) : Effect()
        data class UserNameErrorEffect(@StringRes val message: Int) : Effect()
        data class PasswordErrorEffect(@StringRes val message: Int) : Effect()
        object OnNavigationEffect : Effect()
    }
}