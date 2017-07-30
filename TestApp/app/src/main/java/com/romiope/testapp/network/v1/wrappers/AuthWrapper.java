package com.romiope.testapp.network.v1.wrappers;

import com.romiope.testapp.TokenKeeper;
import com.romiope.testapp.network.entities.LoginRequest;
import com.romiope.testapp.network.entities.LoginResponse;
import com.romiope.testapp.network.v1.endpoints.AuthEndpoint;

import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

public class AuthWrapper extends BaseWrapper {

    private AuthEndpoint authEndpoint = retrofit.create(AuthEndpoint.class);

    public AuthWrapper(Retrofit retrofit) {
        super(retrofit);
    }

    public Observable<Response<LoginResponse>> performLogin(String username, String password) {
        return authEndpoint.performLogin(new LoginRequest(username, password));
    }
}
