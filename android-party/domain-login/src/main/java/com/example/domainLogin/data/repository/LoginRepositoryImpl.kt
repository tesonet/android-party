package com.example.domainLogin.data.repository

import com.example.domainLogin.data.datasource.local.LocalDataSource
import com.example.domainLogin.data.datasource.remote.RemoteDataSource
import com.example.domainLogin.data.mapper.DomainToDtoMapper
import com.example.domainLogin.data.mapper.DtoToDomainMapper
import com.example.domainLogin.domain.model.LoginInfo
import com.example.domainLogin.domain.model.Token
import com.example.domainLogin.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : LoginRepository {
    private val domainToDtoMapper = DomainToDtoMapper()
    private val dtoToDomainMapper = DtoToDomainMapper()
    override fun fetchToken(loginInfo: LoginInfo): Flow<Token> =
        remoteDataSource.fetchToken(loginInfo = domainToDtoMapper.map(loginInfo)).map { tokenDto ->
            dtoToDomainMapper.map(tokenDto)
        }

    override suspend fun saveToken(token: Token) {
       localDataSource.saveToken(token = token.token)
    }
}
