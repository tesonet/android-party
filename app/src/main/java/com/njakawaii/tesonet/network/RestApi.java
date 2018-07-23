package com.njakawaii.tesonet.network;

import com.njakawaii.tesonet.data.LoginModel;
import com.njakawaii.tesonet.data.ServerModelData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @POST("tokens")
    @FormUrlEncoded
    Observable<LoginModel> login(@Field("username") String userName, @Field("password") String password);


    @GET("servers")
    Observable<ServerModelData> getServers();
}
