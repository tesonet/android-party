package com.yasserakbbach.androidparty.login.domain.repository

import com.yasserakbbach.androidparty.login.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveSession(session: Session)
    suspend fun getSession(): Flow<Session>
}