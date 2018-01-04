package com.azzdorfrobotics.android.testio.features.auth;

import com.azzdorfrobotics.android.testio.network.TesonetApi;
import com.azzdorfrobotics.android.testio.network.model.request.LoginRequest;
import com.azzdorfrobotics.android.testio.network.model.response.LoginResponse;
import com.azzdorfrobotics.android.testio.preferences.AppPreferences;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import javax.inject.Inject;

/**
 * Created on 02.01.2018.
 *
 * @author Mykola Tychyna (iMykolaPro)
 */

public class AuthCase {

    private final TesonetApi tesonetApi;
    private final AppPreferences appPreferences;

    @Inject public AuthCase(TesonetApi tesonetApi, AppPreferences appPreferences) {
        this.tesonetApi = tesonetApi;
        this.appPreferences = appPreferences;
    }

    public Observable<LoginResponse> login(@NonNull LoginRequest request) {
        return tesonetApi.login(request).doOnNext(this::cacheToken);
    }

    private void cacheToken(LoginResponse loginResponse) {
        appPreferences.setUserToken(loginResponse.getToken());
    }

    public void logout(){
        appPreferences.setUserToken(null);
    }
}
