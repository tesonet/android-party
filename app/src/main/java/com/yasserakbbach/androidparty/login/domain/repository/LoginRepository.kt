package com.yasserakbbach.androidparty.login.domain.repository

import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.model.Session

interface LoginRepository {

    suspend fun login(
        login: Login,
    ): Result<Session>
}