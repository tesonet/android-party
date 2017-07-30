package com.romiope.testapp.network;

import com.romiope.testapp.network.v1.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    static private RestClient instance = new RestClient();
    private final Api api;

    public Api getApi() {
        return api;
    }

    public static RestClient getInstance() {
        return instance;
    }

    public RestClient() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        api = new Api(builder);
    }
}
