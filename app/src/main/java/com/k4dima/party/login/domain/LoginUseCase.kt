package com.k4dima.androidparty.features.login.domain

import com.k4dima.androidparty.features.app.data.DataRepository
import com.k4dima.androidparty.features.app.data.PreferenceRepository
import com.k4dima.androidparty.features.app.domain.UseCase
import com.k4dima.androidparty.features.login.data.model.Token
import com.k4dima.party.login.ui.di.LoginScope
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

@LoginScope
class LoginUseCase
@Inject
constructor(private val tokenRepository: DataRepository<Map<String, RequestBody>, Token>,
            private val preferenceRepository: PreferenceRepository) : UseCase<Array<String>, String> {
    override fun data(params: Array<String>): Single<String> {
        val requestBodyMap = mapOf<String, RequestBody>(
                "username" to RequestBody.create(MediaType.parse("text/plain"), params[0]),
                "password" to RequestBody.create(MediaType.parse("text/plain"), params[1])
        )
        return tokenRepository.data(requestBodyMap)
                .subscribeOn(Schedulers.io())
                .map { it.token }
                .doOnSuccess { preferenceRepository.token = it }!!
    }
}