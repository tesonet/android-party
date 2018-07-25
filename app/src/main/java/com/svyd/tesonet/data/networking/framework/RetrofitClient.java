package com.svyd.tesonet.data.networking.framework;

import com.svyd.tesonet.data.networking.global.ApiConstants;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class RetrofitClient {
    private static RetrofitClient sInstance;

    private Retrofit mRetrofit;

    private RetrofitClient(OkHttpClient client, Converter.Factory factory) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.API_ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(factory)
                .client(client)
                .build();
    }

    public static RetrofitClient getInstance(OkHttpClient client, Converter.Factory factory) {
        if (sInstance == null) {
            sInstance = new RetrofitClient(client, factory);
        }
        return sInstance;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }
}
