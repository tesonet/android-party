package com.edvinas.balkaitis.party.login.network

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("tokens")
    fun login(@Body tokenRequest: LoginBody): Single<LoginResponse>
}
