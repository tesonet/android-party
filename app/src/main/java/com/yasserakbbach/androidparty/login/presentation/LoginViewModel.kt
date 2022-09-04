package com.yasserakbbach.androidparty.login.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.usecase.GetSessionUseCase
import com.yasserakbbach.androidparty.login.domain.usecase.LoginUseCase
import com.yasserakbbach.androidparty.navigation.UiEvent
import com.yasserakbbach.androidparty.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getSessionUseCase: GetSessionUseCase,
): ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val event = _uiEvent.receiveAsFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val isLoggedIn = getSessionUseCase.invoke()
                .first()
                .token != null

            if(isLoggedIn) {
                _uiEvent.send(UiEvent.NavigateTo(Screen.ListServer))
            }
            state = state.copy(isLoggedIn = isLoggedIn)
        }
    }

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnPasswordChange -> event.updateState()
            is LoginEvent.OnUsernameChange -> event.updateState()
            LoginEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun LoginEvent.OnPasswordChange.updateState() {
        state = state.copy(
            password = password,
            isLoginEnabled = shouldEnableLogin(),
        )
    }

    private fun LoginEvent.OnUsernameChange.updateState() {
        state = state.copy(
            username = username,
            isLoginEnabled = shouldEnableLogin(),
        )
    }

    private fun shouldEnableLogin(): Boolean =
        state.username.isNotEmpty() && state.password.isNotEmpty()

    private fun onLoginClick() {
        state = state.copy(isLoading = true)

        viewModelScope.launch {
            loginUseCase(
                Login(
                    username = state.username,
                    password = state.password,
                )
            ).onSuccess {
                _uiEvent.send(
                    UiEvent.NavigateTo(Screen.ListServer)
                )
            }.onFailure {
                Log.d("LOGIN_EXCEPTION", it.message ?: "Unknown error")
                state = state.copy(
                    error = "Invalid credentials",
                    isLoading = false,
                )
            }
        }
    }
}