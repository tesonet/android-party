package com.tzemaitis.androidparty;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by tzemaitis on 25/07/18.
 */

public interface TesonetApiInterface {

    @POST("tokens")
    Call<AuthenticationToken> login(@Body UserAccount account);

    @GET("servers")
    Call<ArrayList<Server>> getServers(@Header("Authorization") String authorization);
}
