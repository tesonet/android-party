package com.yegor.tesonet.partyapp.networking;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.yegor.tesonet.partyapp.framework.ApplicationSingleton;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Custom interceptor made to include all headers
 */
class CustomInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationSingleton.getInstance());
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        builder.addHeader("Content-Type", "application/json");
        if (sharedPreferences.contains(ServiceProvider.KEY_TOKEN)) {
            builder.addHeader("Authorization", sharedPreferences.getString(ServiceProvider.KEY_TOKEN, ""));
        }
        originalRequest = builder.build();
        return chain.proceed(originalRequest);
    }
}
