package com.example.domainLogin.domain.repository

import com.example.domainLogin.domain.model.LoginInfo
import com.example.domainLogin.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun fetchToken(loginInfo: LoginInfo): Flow<Token>
    suspend fun saveToken(token: Token)
}
