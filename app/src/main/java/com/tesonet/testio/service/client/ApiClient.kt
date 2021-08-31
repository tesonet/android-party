package com.tesonet.testio.service.client

import com.tesonet.testio.service.data.token.Token
import com.tesonet.testio.service.data.user.RequestUser
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiClient {

    @Headers(CONTENT_TYPE)
    @POST("v1/tokens")
    fun requestUserToken(@Body requestUser: RequestUser): Observable<Token>

    companion object {
        const val CONTENT_TYPE: String = "Content-Type: application/json"
    }
}