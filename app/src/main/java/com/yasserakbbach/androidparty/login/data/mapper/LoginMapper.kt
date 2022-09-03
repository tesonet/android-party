package com.yasserakbbach.androidparty.login.data.mapper

import com.yasserakbbach.androidparty.login.data.remote.LoginRequest
import com.yasserakbbach.androidparty.login.data.remote.LoginResponse
import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.model.Session

fun Login.toLoginRequest() : LoginRequest =
    LoginRequest(
        username = username,
        password = password,
    )

fun LoginResponse.toSession(): Session =
    Session(token = token)