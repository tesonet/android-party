package com.baruckis.androidparty.domain.repository

import com.baruckis.androidparty.domain.entity.TokenEntity
import io.reactivex.Single

interface MainRepository {

    fun sendAuthorization(username: String, password: String): Single<TokenEntity>
}