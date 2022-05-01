package com.czech.androidparty.network

import com.czech.androidparty.models.DataList
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.models.LoginResponse
import com.czech.androidparty.utils.Routes
import io.ktor.client.*
import io.ktor.client.request.*

class ApiServiceImpl(
    private val client: HttpClient,
    private val baseUrl: String
): ApiService {
    override suspend fun login(userData: LoginRequest): LoginResponse {
        return client.post{
            url(Routes.LOGIN)
            body = userData
        }
    }

    override suspend fun getList(): List<DataList> {
        return client.get {
            url(Routes.GET_LIST)
        }
    }
}