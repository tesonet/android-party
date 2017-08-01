package com.yegor.tesonet.partyapp.networking;

import com.yegor.tesonet.partyapp.model.Account;
import com.yegor.tesonet.partyapp.model.Server;
import com.yegor.tesonet.partyapp.model.TokenResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Beck-end service
 */
interface TesonetService {

    @POST("tokens")
    Observable<TokenResponse> login(@Body Account account);

    @GET("servers")
    Observable<List<Server>> fetchList();
}
