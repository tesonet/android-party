package com.romiope.testapp.network.v1;

import retrofit2.Retrofit;

public abstract class BaseApi {

    protected Retrofit retrofit;

    public BaseApi(Retrofit.Builder builder) {
        builder.baseUrl("http://playground.tesonet.lt/v1/");
        retrofit = builder.build();
    }
}
