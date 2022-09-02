package com.czech.androidparty.datasource.network

import com.czech.androidparty.models.DataList
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.models.LoginResponse

interface ApiService {

    suspend fun login(userData: LoginRequest): LoginResponse

    suspend fun getList(token: String): List<DataList>?
}