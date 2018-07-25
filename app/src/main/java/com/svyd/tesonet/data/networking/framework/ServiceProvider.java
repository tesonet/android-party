package com.svyd.tesonet.data.networking.framework;


import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    public <T> T provideService(Class<T> clazz) {
        GsonBuilder builder = new GsonBuilder();
        return RetrofitClient.getInstance(
                HttpClient.getInstance().getClient(),
                GsonConverterFactory.create(builder.create()))
                .getRetrofit()
                .create(clazz);
    }
}
