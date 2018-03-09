package com.tesonet.example.android_party.communication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.tesonet.example.android_party.utils.AppProperties;
import com.tesonet.example.android_party.utils.Preferences;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Vilius on 2018-03-08.
 */

public abstract class BaseOkHttpClient extends OkHttpClient {
    protected static final String TAG = "BoHC";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected static OkHttpClient client;
    protected Context context;
    protected String endpoint;

    private static final String TOKEN_HEADER = "Authorization";

    public BaseOkHttpClient(final Context context) {
        String host = AppProperties.getHost(context);
        this.context = context;
        this.endpoint = host;

        if (client == null) {
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    Request requestWithHeaders;
                    if (Preferences.getTokenValue(context) != null && !Preferences.getTokenValue(context).isEmpty()) {
                        requestWithHeaders = originalRequest.newBuilder()
                                .addHeader(TOKEN_HEADER, "Bearer "+Preferences.getTokenValue(context))
                                .build();
                    } else {
                        requestWithHeaders = originalRequest.newBuilder().build();
                    }
                    return chain.proceed(requestWithHeaders);
                }
            };

            client = new Builder()
                    .addInterceptor(headerInterceptor)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
        }
    }

    protected String getEndpoint() {return endpoint;}

    protected void setEndpoint(String method) {
        endpoint = getEndpoint().concat(method);
    }

    protected Request buildPostRequest(Object jsonObject) {
        RequestBody requestBody = RequestBody.create(JSON, new Gson().toJson(jsonObject));
        return new Request.Builder()
                .url(getEndpoint())
                .post(requestBody)
                .build();
    }

    protected Request buildGetRequest() {
        return new Request.Builder()
                .url(getEndpoint())
                .get()
                .build();
    }
}
