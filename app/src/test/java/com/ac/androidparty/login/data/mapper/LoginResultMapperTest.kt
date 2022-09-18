package com.ac.androidparty.login.data.mapper

import com.ac.androidparty.login.data.remote.Token
import com.ac.androidparty.login.data.repository.LoginResult
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class LoginResultMapperTest {
    private val token = Token("token")
    private val throwable: Throwable = Throwable("401")

    @Test
    fun `should map token if it exists`() {
        LoginResultMapper(token) shouldBe LoginResult.Success(token)
    }

    @Test
    fun `should map wrong credentials when error contains 401`() {
        ErrorResultMapper(throwable) shouldBe LoginResult.WrongCredentials
    }

    @Test
    fun `should map error for unknown error`() {
        ErrorResultMapper(Throwable("1234")) shouldBe LoginResult.Error
    }
}