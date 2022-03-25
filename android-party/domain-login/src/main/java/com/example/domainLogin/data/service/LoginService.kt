package com.example.domainLogin.data.service

import com.example.domainLogin.data.dto.LoginDto
import com.example.domainLogin.data.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("tokens")
    suspend fun getToken(
        @Body loginInfo: LoginDto
    ): TokenDto
}
