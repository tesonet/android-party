package com.baruckis.data.repository

import com.baruckis.data.mapper.TokenMapper
import com.baruckis.data.model.TokenData
import com.baruckis.domain.entity.TokenEntity
import com.baruckis.domain.repository.LoginRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val mapper: TokenMapper,
    private val remoteDataSource: RemoteDataSource
) : LoginRepository {

    override fun sendAuthorization(username: String, password: String): Single<TokenEntity> {
        return remoteDataSource.sendAuthorization(username, password).map { tokenData: TokenData ->
            mapper.mapFrom(tokenData)
        }
    }
}