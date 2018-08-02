package com.axborn.androidparty.features.rest;

import com.axborn.androidparty.structure.Server;
import com.axborn.androidparty.structure.TokensResponse;
import com.axborn.androidparty.structure.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestAPI {
    String BASE_URL = "http://playground.tesonet.lt/v1/";

    @POST("tokens")
    Call<TokensResponse> retrieveToken (@Body User user);

    @GET("servers")
    Call<List<Server>> retrieveServerList (@Header("Authorization") String token);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
