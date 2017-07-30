package com.romiope.testapp.network.v1.endpoints;

import com.romiope.testapp.network.entities.ServerResponse;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

public interface ServersEndpoint {

    @GET("servers")
    Observable<Response<ServerResponse[]>> getServers(@Header("Authorization") String token); // : Bearer
}
