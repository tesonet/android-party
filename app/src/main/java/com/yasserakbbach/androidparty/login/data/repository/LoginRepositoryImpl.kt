package com.yasserakbbach.androidparty.login.data.repository

import com.yasserakbbach.androidparty.login.data.mapper.toLoginRequest
import com.yasserakbbach.androidparty.login.data.mapper.toSession
import com.yasserakbbach.androidparty.login.data.remote.LoginApi
import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.model.Session
import com.yasserakbbach.androidparty.login.domain.repository.LoginRepository
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import java.lang.Exception

class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val sessionRepository: SessionRepository,
): LoginRepository {

    override suspend fun login(login: Login): Result<Session> =
        try {
            val response = loginApi.login(login.toLoginRequest())
            val session = response.toSession()
            sessionRepository.saveSession(session)
            Result.success(session)
        } catch (e: Exception) {
            Result.failure(e)
        }
}