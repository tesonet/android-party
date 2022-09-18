package com.ac.androidparty.login.data.repository

import android.util.Log
import com.ac.androidparty.login.data.mapper.ErrorResultMapper
import com.ac.androidparty.login.data.mapper.LoginResultMapper
import com.ac.androidparty.login.data.remote.LoginApi
import com.ac.androidparty.login.data.repository.LoginResult.Error
import com.ac.androidparty.login.domain.model.Login
import com.ac.androidparty.login.domain.model.asLoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
) : LoginRepository {

    private val mapper: LoginResultMapper = LoginResultMapper

    override suspend fun login(login: Login): LoginResult =
        withContext(Dispatchers.IO) {
            runCatching {
                RetryExecutor.execute({ execute(login) }, MAX_ATTEMPTS)
            }.onFailure {
                Error
            }.getOrNull() ?: Error
        }

    private suspend fun execute(login: Login): LoginResult = mapper(
        loginApi.login(
            login.asLoginRequest()
        )
    )

    private companion object {
        const val MAX_ATTEMPTS = 3
    }
}

private object RetryExecutor {
    private var count = 0

    suspend fun execute(action: suspend () -> LoginResult, attempts: Int): LoginResult {
        count++
        return try {
            action.invoke()
        } catch (throwable: Throwable) {
            Log.w("LoginRetryExecutor", throwable.toString())
            if (count < attempts) {
                delay(DELAY)
                execute(action, attempts)
            } else {
                ErrorResultMapper(throwable)
            }
        }
    }

    private const val DELAY = 1000L
}