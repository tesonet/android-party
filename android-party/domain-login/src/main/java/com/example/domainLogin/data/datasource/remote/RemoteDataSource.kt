package com.example.domainLogin.data.datasource.remote

import com.example.domainLogin.data.dto.LoginDto
import com.example.domainLogin.data.dto.TokenDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun fetchToken(loginInfo: LoginDto): Flow<TokenDto>
}
