package com.ac.androidparty.servers.data.remote

import com.ac.androidparty.core.network.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface ServersApi {
    @GET(SERVERS)
    @Headers(TOKEN_AUTHORIZATION)
    suspend fun getServers(): List<ServerResponse>

    companion object {
        private const val SERVERS = "servers"
        private const val TOKEN_AUTHORIZATION =
            "${NetworkConstants.REQUIRE_AUTHORIZATION}: ${NetworkConstants.LOGIN_TOKEN}"
    }
}