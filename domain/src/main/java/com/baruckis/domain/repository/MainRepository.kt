package com.baruckis.domain.repository

import com.baruckis.domain.entity.TokenEntity
import io.reactivex.Single

interface MainRepository {

    fun sendAuthorization(username: String, password: String): Single<TokenEntity>
}