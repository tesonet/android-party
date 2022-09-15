package com.ac.androidparty.login.presentation

import com.ac.androidparty.login.data.repository.LoginResult

internal interface LoginState {
    object Initial : LoginState
    object Loading : LoginState
    object Error : LoginState
    object WrongCredentials : LoginState
    data class Success(val token: String) : LoginState
}

internal object LoginStateMapper {
    operator fun invoke(loginResult: LoginResult) =
        when (loginResult) {
            is LoginResult.Success -> LoginState.Success(loginResult.token.token)
            is LoginResult.Error -> LoginState.Error
            is LoginResult.WrongCredentials -> LoginState.WrongCredentials
        }
}