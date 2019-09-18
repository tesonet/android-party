package com.example.tesonet;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {

    @POST("tokens")
    Call<User> login(@Body Login login);

    @GET("servers")
    Call<List<Server>> getServer(@Header("Authorization") String authToken);
}
