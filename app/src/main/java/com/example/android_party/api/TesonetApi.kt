package com.example.android_party.api

import com.example.android_party.data.Server
import com.example.android_party.data.Token
import io.reactivex.Observable
import retrofit2.http.*

/**
 * The interface which provides methods to get result of webservices
 */
interface TesonetApi {
    /**
     * Get the auth token from the API
     */
    @POST("/v1/tokens?")
    fun getAuthToken(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<Token>

    /**
     * Get ther servers list from the API
     */
    @GET("v1/servers")
    fun getServers(@Header("Authorization") authorization: String): Observable<List<Server>>
}