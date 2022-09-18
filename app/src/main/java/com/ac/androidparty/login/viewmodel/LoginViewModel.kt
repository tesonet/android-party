package com.ac.androidparty.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ac.androidparty.login.data.repository.LoginRepository
import com.ac.androidparty.login.data.repository.LoginResult
import com.ac.androidparty.login.domain.model.Login
import com.ac.androidparty.login.domain.preferences.LoginPreferences
import com.ac.androidparty.login.presentation.LoginState
import com.ac.androidparty.login.presentation.LoginStateMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val loginPreferences: LoginPreferences
) : ViewModel() {

    private var credentials = Login("", "")

    private val _state = MutableStateFlow<LoginState>(LoginState.LoggedOut)
    val state: StateFlow<LoginState>
        get() = _state

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameChanged -> {
                credentials = credentials.copy(username = event.username.trim())
                _state.value = LoginState.LoggedOut
            }
            is LoginEvent.PasswordChanged -> {
                credentials = credentials.copy(password = event.password.trim())
                _state.value = LoginState.LoggedOut
            }
            is LoginEvent.Login -> {
                login()
                if (_state.value is LoginState.Success) event.navigateToServers()
            }
            else -> throw IllegalStateException("Event not implemented")
        }
    }

    private val viewStateMapper: LoginStateMapper = LoginStateMapper

    private fun login() {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            flowOf(
                loginRepository.login(
                    login = credentials
                )
            ).collect(::applyLoginResult)
        }
    }

    private fun applyLoginResult(loginResult: LoginResult) = viewStateMapper(loginResult).run {
        _state.value = this
        if (this is LoginState.Success) {
            loginPreferences.token = this.token
        }
    }
}