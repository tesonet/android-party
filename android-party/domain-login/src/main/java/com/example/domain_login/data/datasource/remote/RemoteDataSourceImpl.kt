package com.example.domain_login.data.datasource.remote

import com.example.domain_login.data.dto.LoginDto
import com.example.domain_login.data.dto.TokenDto
import com.example.domain_login.data.service.LoginService
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