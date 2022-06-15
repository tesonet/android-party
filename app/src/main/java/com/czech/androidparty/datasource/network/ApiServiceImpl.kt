package com.czech.androidparty.datasource.network

import android.annotation.SuppressLint
import android.util.Log
import com.czech.androidparty.models.DataList
import com.czech.androidparty.models.LoginRequest
import com.czech.androidparty.models.LoginResponse
import com.czech.androidparty.utils.Routes
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val client: HttpClient,
    private val baseUrl: String
): ApiService {
    override suspend fun login(userData: LoginRequest): LoginResponse {
        return try {
            client.post{
                url(Routes.LOGIN)
                contentType(ContentType.Application.Json)
                body = userData
            }
        } catch (e: ClientRequestException) {
            LoginResponse(
                message = "Error: ${e.response.status}"
            )
        } catch (e: ServerResponseException) {
            LoginResponse(
                message = "Error: ${e.response.status}"
            )
        } catch (e: RedirectResponseException) {
            LoginResponse(
                message = "Error: ${e.response.status}"
            )
        }
    }

    @SuppressLint("LongLogTag")
    override suspend fun getList(token: String): List<DataList>? {
        return try {
            client.get {
                url(Routes.GET_LIST)
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Authorization, token)
            }
        } catch (e: ClientRequestException) {
            Log.e("DataClientRequestException", "Error: ${e.response.status}")
            null
        } catch (e: ServerResponseException) {
            Log.e("DataServerResponseException", "Error: ${e.response.status}")
            null
        } catch (e: RedirectResponseException) {
            Log.e("DataRedirectResponseException", "Error: ${e.response.status}")
            null
        }
    }
}