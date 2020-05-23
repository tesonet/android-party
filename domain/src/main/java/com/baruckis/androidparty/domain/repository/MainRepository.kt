package com.baruckis.androidparty.domain.repository

import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.entity.TokenEntity
import io.reactivex.Single

interface MainRepository {

    fun getLoggedInUser(): LoggedInUserEntity?

    fun login(username: String, password: String): Single<TokenEntity>

    fun logout()
}