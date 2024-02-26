package feature.login.domain

import core.data.repository.TokenRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import signin.domain.LoginUseCase
import javax.inject.Inject

class TestioLoginUseCase @Inject constructor(private val tokenRepository: TokenRepository) :
    LoginUseCase {
    override suspend fun invoke(username: String, password: String) =
        mapOf("username" to username, "password" to password)
            .mapValues { (_, value) -> value.toRequestBody("text/plain".toMediaTypeOrNull()) }
            .let {
                val token = tokenRepository.newToken(it).token
                tokenRepository.saveToken(token)
            }
}