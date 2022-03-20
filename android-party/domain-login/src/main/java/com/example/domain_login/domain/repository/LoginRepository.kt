package com.example.domain_login.domain.repository

import com.example.domain_login.domain.model.LoginInfo
import com.example.domain_login.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun fetchToken(loginInfo:LoginInfo): Flow<Token>
    suspend fun saveToken(token: Token)
}