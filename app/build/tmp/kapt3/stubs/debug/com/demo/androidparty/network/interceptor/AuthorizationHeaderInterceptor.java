package com.demo.androidparty.network.interceptor;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u0010"}, d2 = {"Lcom/demo/androidparty/network/interceptor/AuthorizationHeaderInterceptor;", "Lokhttp3/Interceptor;", "preferences", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "(Lcom/demo/androidparty/storage/preferences/AppPreferences;)V", "token", "", "getToken", "()Ljava/lang/String;", "token$delegate", "Lkotlin/Lazy;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "app_debug"})
public final class AuthorizationHeaderInterceptor implements okhttp3.Interceptor {
    private final kotlin.Lazy token$delegate = null;
    private final com.demo.androidparty.storage.preferences.AppPreferences preferences = null;
    private static final java.lang.String HEADER_AUTHORIZATION = "Authorization";
    public static final com.demo.androidparty.network.interceptor.AuthorizationHeaderInterceptor.Companion Companion = null;
    
    private final java.lang.String getToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull()
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
    
    @javax.inject.Inject()
    public AuthorizationHeaderInterceptor(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.preferences.AppPreferences preferences) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/demo/androidparty/network/interceptor/AuthorizationHeaderInterceptor$Companion;", "", "()V", "HEADER_AUTHORIZATION", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}