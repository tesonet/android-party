package com.k4dima.party.login.domain

import com.k4dima.party.app.data.DataRepository
import com.k4dima.party.app.data.PreferenceRepository
import com.k4dima.party.app.domain.UseCase
import com.k4dima.party.login.data.model.Token
import com.k4dima.party.login.ui.di.LoginScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

@LoginScope
class LoginUseCase
@Inject
constructor(private val tokenRepository: DataRepository<Map<String, RequestBody>, Token>,
            private val preferenceRepository: PreferenceRepository) : UseCase<Array<String>, String> {
    override suspend fun data(params: Array<String>): String {
        val requestBodyMap = mapOf<String, RequestBody>(
                "username" to RequestBody.create(MediaType.parse("text/plain"), params[0]),
                "password" to RequestBody.create(MediaType.parse("text/plain"), params[1])
        )
        return withContext(Dispatchers.IO) {
            tokenRepository.data(requestBodyMap).token.also { preferenceRepository.token = it }
        }
    }
}