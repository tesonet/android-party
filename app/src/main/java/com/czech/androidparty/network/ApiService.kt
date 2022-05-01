package com.czech.androidparty.network

import com.czech.androidparty.models.DataList
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.models.LoginResponse

interface ApiService {

    suspend fun login(userData: LoginRequest): LoginResponse

    suspend fun getList(): List<DataList>
}