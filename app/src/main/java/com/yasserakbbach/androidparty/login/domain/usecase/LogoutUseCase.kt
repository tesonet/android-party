package com.yasserakbbach.androidparty.login.domain.usecase

import com.yasserakbbach.androidparty.login.domain.model.Session
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository

class LogoutUseCase(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke() {
        sessionRepository.saveSession(
            session = Session(token = null)
        )
    }
}