package com.demo.androidparty.ui.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u00a2\u0006\u0002\b\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/demo/androidparty/ui/login/LoginModel;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "apiService", "Lcom/demo/androidparty/network/ApiService;", "prefs", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lcom/demo/androidparty/network/ApiService;Lcom/demo/androidparty/storage/preferences/AppPreferences;)V", "login", "Lcom/demo/androidparty/utils/NetworkResult;", "Lcom/demo/androidparty/dto/TokenData;", "data", "Lcom/demo/androidparty/dto/LoginData;", "login$app_debug", "(Lcom/demo/androidparty/dto/LoginData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "save", "", "token", "", "save$app_debug", "app_debug"})
public final class LoginModel {
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    private final com.demo.androidparty.network.ApiService apiService = null;
    private final com.demo.androidparty.storage.preferences.AppPreferences prefs = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object login$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.dto.LoginData data, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.demo.androidparty.utils.NetworkResult<com.demo.androidparty.dto.TokenData>> p1) {
        return null;
    }
    
    public final void save$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public LoginModel(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.IO()
    kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.network.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.preferences.AppPreferences prefs) {
        super();
    }
}