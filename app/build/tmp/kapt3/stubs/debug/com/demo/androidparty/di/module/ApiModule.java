package com.demo.androidparty.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!\u00a2\u0006\u0002\b\u0007\u00a8\u0006\t"}, d2 = {"Lcom/demo/androidparty/di/module/ApiModule;", "", "()V", "bindAuthorizationInterceptor", "Lokhttp3/Interceptor;", "interceptor", "Lcom/demo/androidparty/network/interceptor/AuthorizationHeaderInterceptor;", "bindAuthorizationInterceptor$app_debug", "Companion", "app_debug"})
@dagger.Module()
public abstract class ApiModule {
    public static final com.demo.androidparty.di.module.ApiModule.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.InterceptorAuthorizationHeader()
    @dagger.Binds()
    public abstract okhttp3.Interceptor bindAuthorizationInterceptor$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.network.interceptor.AuthorizationHeaderInterceptor interceptor);
    
    private ApiModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final retrofit2.Retrofit buildRetrofit$app_debug(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient.Builder okHttpClientBuilder) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final okhttp3.OkHttpClient provideUnauthorisedOkHttpClientBuilder(@org.jetbrains.annotations.NotNull()
    okhttp3.logging.HttpLoggingInterceptor interceptor) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientAuthorized()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final okhttp3.OkHttpClient provideAuthorisedOkHttpClientBuilder$app_debug(@org.jetbrains.annotations.NotNull()
    okhttp3.logging.HttpLoggingInterceptor interceptor, @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.InterceptorAuthorizationHeader()
    okhttp3.Interceptor authInterceptor) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final okhttp3.logging.HttpLoggingInterceptor provideHttpLoggingInterceptor$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientAuthorized()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final com.demo.androidparty.network.ApiService provideAuthorisedApiService$app_debug(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientAuthorized()
    okhttp3.OkHttpClient client) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final com.demo.androidparty.network.ApiService provideUnauthorisedApiService$app_debug(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
    okhttp3.OkHttpClient client) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0001\u00a2\u0006\u0002\b\tJ\u0017\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\fJ\u001f\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u0001\u00a2\u0006\u0002\b\u0012J\r\u0010\u0013\u001a\u00020\u000fH\u0001\u00a2\u0006\u0002\b\u0014J\u0017\u0010\u0015\u001a\u00020\u000b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\u0016J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00a8\u0006\u0018"}, d2 = {"Lcom/demo/androidparty/di/module/ApiModule$Companion;", "", "()V", "buildRetrofit", "Lretrofit2/Retrofit;", "client", "Lokhttp3/OkHttpClient;", "okHttpClientBuilder", "Lokhttp3/OkHttpClient$Builder;", "buildRetrofit$app_debug", "provideAuthorisedApiService", "Lcom/demo/androidparty/network/ApiService;", "provideAuthorisedApiService$app_debug", "provideAuthorisedOkHttpClientBuilder", "interceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "authInterceptor", "Lokhttp3/Interceptor;", "provideAuthorisedOkHttpClientBuilder$app_debug", "provideHttpLoggingInterceptor", "provideHttpLoggingInterceptor$app_debug", "provideUnauthorisedApiService", "provideUnauthorisedApiService$app_debug", "provideUnauthorisedOkHttpClientBuilder", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final retrofit2.Retrofit buildRetrofit$app_debug(@org.jetbrains.annotations.NotNull()
        okhttp3.OkHttpClient.Builder okHttpClientBuilder) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final okhttp3.OkHttpClient provideUnauthorisedOkHttpClientBuilder(@org.jetbrains.annotations.NotNull()
        okhttp3.logging.HttpLoggingInterceptor interceptor) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientAuthorized()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final okhttp3.OkHttpClient provideAuthorisedOkHttpClientBuilder$app_debug(@org.jetbrains.annotations.NotNull()
        okhttp3.logging.HttpLoggingInterceptor interceptor, @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.InterceptorAuthorizationHeader()
        okhttp3.Interceptor authInterceptor) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final okhttp3.logging.HttpLoggingInterceptor provideHttpLoggingInterceptor$app_debug() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientAuthorized()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final com.demo.androidparty.network.ApiService provideAuthorisedApiService$app_debug(@org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientAuthorized()
        okhttp3.OkHttpClient client) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final com.demo.androidparty.network.ApiService provideUnauthorisedApiService$app_debug(@org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
        okhttp3.OkHttpClient client) {
            return null;
        }
        
        private final retrofit2.Retrofit buildRetrofit(okhttp3.OkHttpClient client) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}