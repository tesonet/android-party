package com.edvinas.balkaitis.party.servers.network

import io.reactivex.Single
import retrofit2.http.GET

interface ServersService {
    @GET("servers")
    fun getServers(): Single<List<Server>>
}
