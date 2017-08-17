package com.example.lukas.tesonettest.api

import com.example.lukas.tesonettest.model.Login
import com.example.lukas.tesonettest.model.Server
import com.example.lukas.tesonettest.model.Token
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by lukas on 17.8.17.
 */
interface AppService {
	@POST("tokens")
	fun login(@Body body: Login): Observable<Token>

	@GET("servers")
	fun getServers(): Observable<List<Server>>

}