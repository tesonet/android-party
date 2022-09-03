package com.yasserakbbach.androidparty.login.domain.usecase

import com.yasserakbbach.androidparty.login.domain.model.Login
import com.yasserakbbach.androidparty.login.domain.model.Session
import com.yasserakbbach.androidparty.login.domain.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(login: Login): Result<Session> =
        loginRepository.login(login)
}