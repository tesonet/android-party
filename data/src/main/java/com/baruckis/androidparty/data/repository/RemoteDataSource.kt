package com.baruckis.androidparty.data.repository

import com.baruckis.androidparty.data.model.TokenData
import io.reactivex.Single

interface RemoteDataSource {

    fun sendAuthorization(username: String, password: String): Single<TokenData>

}