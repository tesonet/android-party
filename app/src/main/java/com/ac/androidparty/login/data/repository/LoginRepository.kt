package com.ac.androidparty.login.data.repository

import com.ac.androidparty.login.data.remote.Token
import com.ac.androidparty.login.domain.model.Login

internal interface LoginRepository {
    suspend fun login(login: Login): LoginResult
}

internal sealed interface LoginResult {
    data class Success(val token: Token) : LoginResult
    object Error : LoginResult
    object WrongCredentials : LoginResult
}