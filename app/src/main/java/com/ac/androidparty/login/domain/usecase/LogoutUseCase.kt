package com.ac.androidparty.login.domain.usecase

import com.ac.androidparty.login.domain.preferences.LoginPreferences
import javax.inject.Inject

internal interface LogoutUseCase {
    fun logout(): Boolean
}

internal class LogoutUseCaseImpl @Inject constructor(
    private val loginPreferences: LoginPreferences
) : LogoutUseCase {
    override fun logout() = !loginPreferences.invalidateToken()
}