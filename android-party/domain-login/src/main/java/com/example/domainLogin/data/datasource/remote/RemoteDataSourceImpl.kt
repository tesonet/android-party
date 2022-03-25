package com.example.domainLogin.data.datasource.remote

import com.example.domainLogin.data.dto.LoginDto
import com.example.domainLogin.data.dto.TokenDto
import com.example.domainLogin.data.service.LoginService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: LoginService
) : RemoteDataSource {
    override fun fetchToken(loginInfo: LoginDto): Flow<TokenDto> = flow {
        emit(service.getToken(loginInfo))
    }
}
