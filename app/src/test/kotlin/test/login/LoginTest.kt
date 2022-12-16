package test.login

import app.data.api.ApiModule
import kotlinx.coroutines.runBlocking
import login.domain.TokenRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert
import org.junit.Test

class LoginTest {
    @Test
    fun success() {
        val tokenRepository = ApiModule
            .tesonetService()
            .let { TokenRepository(it) }
        val map = mapOf(
            "username" to "tesonet",
            "password" to "partyanimal"
        ).mapValues { it.value.toRequestBody("text/plain".toMediaTypeOrNull()) }
        runBlocking { tokenRepository.data(map) }
            .token
            .length
            .let { Assert.assertEquals(32, it) }
    }
}