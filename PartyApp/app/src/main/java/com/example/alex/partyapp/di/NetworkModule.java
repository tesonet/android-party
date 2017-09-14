package com.example.alex.partyapp.di;

import com.example.alex.partyapp.network.ContentTypeInterceptor;
import com.example.alex.partyapp.network.TesonetApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.alex.partyapp.network.TesonetApi.API_URL;

@Module
public class NetworkModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Provides
    @Singleton
    TesonetApi provideTesonetApi(Retrofit.Builder retrofit) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ContentTypeInterceptor())
                .build();
        return retrofit.client(client).baseUrl(API_URL).build().create(TesonetApi.class);
    }

}