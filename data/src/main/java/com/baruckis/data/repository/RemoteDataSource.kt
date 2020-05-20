package com.baruckis.data.repository

import com.baruckis.data.model.TokenData
import io.reactivex.Single

interface RemoteDataSource {

    fun sendAuthorization(username: String, password: String): Single<TokenData>

}