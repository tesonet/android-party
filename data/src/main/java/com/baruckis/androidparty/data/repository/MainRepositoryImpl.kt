package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.mapper.TokenMapper
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.repository.MainRepository
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val tokenMapper: TokenMapper,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MainRepository {

    override fun sendAuthorization(username: String, password: String): Single<TokenEntity> {
        return remoteDataSource.sendAuthorization(username, password).map { tokenData: TokenData ->
            tokenMapper.mapFrom(tokenData)
        }
    }
}