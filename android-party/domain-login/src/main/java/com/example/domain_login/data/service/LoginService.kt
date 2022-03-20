package com.example.domain_login.data.service

import com.example.domain_login.data.dto.LoginDto
import com.example.domain_login.data.dto.TokenDto
import com.example.domain_login.domain.model.LoginInfo
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @POST("tokens")
    suspend fun getToken(
        @Body loginInfo: LoginDto
    ): TokenDto
}