package com.yasserakbbach.androidparty.login.data.repository

import com.yasserakbbach.androidparty.login.data.mapper.toLoginRequest
import com.yasserakbbach.androidparty.login.data.mapper.toSession
import com.yasserakbbach.androidparty.login.data.remote.LoginApi
import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.model.Session
import com.yasserakbbach.androidparty.login.domain.repository.LoginRepository
import java.lang.Exception
import javax.inject.Inject

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
): LoginRepository {

    override suspend fun login(login: Login): Result<Session> =
        try {
            val response = loginApi.login(login.toLoginRequest())
            Result.success(response.toSession())
        } catch (e: Exception) {
            Result.failure(e)
        }
}