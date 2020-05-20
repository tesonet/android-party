package com.baruckis.remote

import com.baruckis.data.model.TokenData
import com.baruckis.data.repository.RemoteDataSource
import com.baruckis.remote.api.TesonetApiService
import com.baruckis.remote.mapper.ResponseTokenMapper
import com.baruckis.remote.model.RequestUser
import com.baruckis.remote.model.ResponseToken
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: TesonetApiService,
    private val apiResponseTokenMapper: ResponseTokenMapper
) : RemoteDataSource {

    override fun sendAuthorization(username: String, password: String): Single<TokenData> {
        return apiService.sendAuthorization(RequestUser(username, password))
            .map { response: ResponseToken ->
                apiResponseTokenMapper.mapFrom(response)
            }
    }

}