package com.example.domain_login.data.datasource.remote

import com.example.domain_login.data.dto.LoginDto
import com.example.domain_login.data.dto.TokenDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun fetchToken(loginInfo: LoginDto): Flow<TokenDto>
}