package com.ac.androidparty.login.presentation

import com.ac.androidparty.login.data.repository.LoginResult

internal object LoginStateMapper {
    operator fun invoke(loginResult: LoginResult) =
        when (loginResult) {
            is LoginResult.Success -> LoginState.Success(loginResult.token.token)
            is LoginResult.Error -> LoginState.Error
            is LoginResult.WrongCredentials -> LoginState.WrongCredentials
        }
}