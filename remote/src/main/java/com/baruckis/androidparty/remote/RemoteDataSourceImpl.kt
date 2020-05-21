package com.baruckis.androidparty.remote

import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.data.repository.RemoteDataSource
import com.baruckis.androidparty.remote.api.TesonetApiService
import com.baruckis.androidparty.remote.mapper.ResponseTokenMapper
import com.baruckis.androidparty.remote.model.RequestUser
import com.baruckis.androidparty.remote.model.ResponseToken
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: TesonetApiService,
    private val apiResponseTokenMapper: ResponseTokenMapper
) : RemoteDataSource {

    override fun sendAuthorization(username: String, password: String): Single<TokenData> {
        return apiService.sendAuthorization(
            RequestUser(
                username,
                password
            )
        )
            .map { response: ResponseToken ->
                apiResponseTokenMapper.mapFrom(response)
            }
    }

}