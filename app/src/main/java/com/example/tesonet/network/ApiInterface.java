package com.example.tesonet.network;

import com.example.tesonet.database.models.Server;
import com.example.tesonet.database.models.Token;
import com.example.tesonet.database.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("tokens")
    Call<Token> login(@Body User user);

    @GET("servers")
    Call<List<Server>> getServer(@Header("Authorization") String authToken);
}
