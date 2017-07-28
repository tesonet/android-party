package com.yegor.tesonet.partyapp.networking;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Services factory
 */
public class ServiceProvider {

    private static final String API_BASE_URL = "http://playground.tesonet.lt/v1/";
    public static final String KEY_TOKEN = "KEY_TOKEN";

    /**
     * creates service of given type
     *
     * @param serviceClass service class
     * @param <S>          service type
     * @return service instance
     */
    static <S> S createService(Class<S> serviceClass) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor())
                .build();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));
        Retrofit retrofit = builder.client(okHttpClient).build();
        return retrofit.create(serviceClass);
    }
}
