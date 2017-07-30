package com.romiope.testapp.network.v1;

import com.romiope.testapp.network.v1.wrappers.AuthWrapper;
import com.romiope.testapp.network.v1.wrappers.ServersWrapper;

import retrofit2.Retrofit;

public class Api extends BaseApi {

    private AuthWrapper authEndpoint = new AuthWrapper(retrofit);

    private ServersWrapper serversEndpoint = new ServersWrapper(retrofit);

    public Api(Retrofit.Builder builder) {
        super(builder);
    }

    public AuthWrapper getAuthApi() {
        return authEndpoint;
    }

    public ServersWrapper getServersApi() {
        return serversEndpoint;
    }
}
