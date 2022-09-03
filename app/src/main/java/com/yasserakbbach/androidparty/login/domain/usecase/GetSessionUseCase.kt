package com.yasserakbbach.androidparty.login.domain.usecase

import com.yasserakbbach.androidparty.login.domain.model.Session
import com.yasserakbbach.androidparty.login.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow

class GetSessionUseCase(
    private val sessionRepository: SessionRepository,
) {

    suspend operator fun invoke(): Flow<Session> =
        sessionRepository.getSession()
}