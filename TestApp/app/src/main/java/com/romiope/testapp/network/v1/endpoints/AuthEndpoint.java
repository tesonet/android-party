package com.romiope.testapp.network.v1.endpoints;

import com.romiope.testapp.network.entities.LoginRequest;
import com.romiope.testapp.network.entities.LoginResponse;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AuthEndpoint {

    @POST("tokens")
    Observable<Response<LoginResponse>> performLogin(@Body LoginRequest body);
}
