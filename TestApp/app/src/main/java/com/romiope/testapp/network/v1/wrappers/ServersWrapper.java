package com.romiope.testapp.network.v1.wrappers;

import com.romiope.testapp.network.entities.ServerResponse;
import com.romiope.testapp.network.v1.endpoints.ServersEndpoint;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;

public class ServersWrapper extends BaseWrapper {

    private ServersEndpoint serverEndpoint = retrofit.create(ServersEndpoint.class);

    public ServersWrapper(Retrofit retrofit) {
        super(retrofit);
    }

    @GET("servers")
    public Observable<Response<ServerResponse[]>> getServers(String token) {
        return serverEndpoint.getServers(token);
    }
}
