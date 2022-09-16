package com.simplekjl.servers.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simplekjl.domain.model.Login
import com.simplekjl.domain.repository.SessionManager
import com.simplekjl.domain.usecase.LoginUseCase
import com.simplekjl.domain.utils.Result.Error
import com.simplekjl.domain.utils.Result.Success
import com.simplekjl.servers.navigation.NavTarget.ServerList
import com.simplekjl.servers.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val navigator: Navigator,
    private val loginUseCase: LoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val _loginState =
        MutableStateFlow<LoginState>(LoginState.NotLoggedIn)
    val loginState: StateFlow<LoginState> = _loginState

    init {
        if (isLoggedIn()) {
            navigator.navigateTo(ServerList)
        }
    }

    private fun isLoggedIn(): Boolean {
        val token = sessionManager.fetchAuthToken()
        return token.isNotBlank()
    }

    fun signIn(username: String, password: String) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            when (val result = loginUseCase(Login(username, password))) {
                is Error -> {
                    _loginState.value = LoginState.Error
                }
                is Success -> {
                    sessionManager.saveAuthToken(result.data)
                    navigator.navigateTo(ServerList)
                }
            }
        }
    }
}

sealed class LoginState {
    object NotLoggedIn : LoginState()
    object Loading : LoginState()
    object Error : LoginState()
}