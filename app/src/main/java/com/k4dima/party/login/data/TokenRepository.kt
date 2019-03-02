package com.k4dima.androidparty.features.login.data

import com.k4dima.androidparty.features.app.data.DataRepository
import com.k4dima.androidparty.features.app.data.api.TesonetService
import com.k4dima.androidparty.features.login.data.model.Token
import com.k4dima.party.login.ui.di.LoginScope
import okhttp3.RequestBody
import javax.inject.Inject

@LoginScope
class TokenRepository
@Inject
constructor(private val tesonetService: TesonetService) : DataRepository<Map<String, RequestBody>, Token> {
    override fun data(parameter: Map<String, RequestBody>) = tesonetService.token(parameter)
}