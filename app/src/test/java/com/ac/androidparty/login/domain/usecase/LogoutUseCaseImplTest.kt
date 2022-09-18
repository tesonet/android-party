package com.ac.androidparty.login.domain.usecase

import com.ac.androidparty.login.domain.preferences.LoginPreferences
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class LogoutUseCaseImplTest {

    private val loginPreferences: LoginPreferences = mock {
        on { token } doReturn "token"
    }


    private val logoutUseCase: LogoutUseCase =
        LogoutUseCaseImpl(loginPreferences = loginPreferences)

    @Test
    fun `should invalidate token`() {
        logoutUseCase.logout() shouldBe true
    }

}