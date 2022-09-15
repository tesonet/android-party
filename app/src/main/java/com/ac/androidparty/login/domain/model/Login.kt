package com.ac.androidparty.login.domain.model

import com.ac.androidparty.login.data.remote.LoginRequest

data class Login(
    val username: String,
    val password: String
)

internal fun Login.asLoginRequest() = LoginRequest(
    username = username,
    password = password
)