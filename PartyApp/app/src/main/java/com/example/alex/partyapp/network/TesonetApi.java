package com.example.alex.partyapp.network;

import com.example.alex.partyapp.data.Account;
import com.example.alex.partyapp.data.Token;
import com.example.alex.partyapp.data.Server;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TesonetApi {

    String API_URL = "http://playground.tesonet.lt/v1/";

    @POST("tokens")
    Call<Token> login(@Body Account account);

    @GET("servers")
    Call<List<Server>> getServers(@Header("Authorization") String authorization);
}
