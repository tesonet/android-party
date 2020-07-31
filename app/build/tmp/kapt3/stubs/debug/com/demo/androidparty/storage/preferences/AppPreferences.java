package com.demo.androidparty.storage.preferences;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0006H\u0000\u00a2\u0006\u0002\b\u0007J\u000f\u0010\b\u001a\u0004\u0018\u00010\tH\u0000\u00a2\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\tH\u0000\u00a2\u0006\u0002\b\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/demo/androidparty/storage/preferences/AppPreferences;", "", "preferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "clear", "", "clear$app_debug", "getToken", "", "getToken$app_debug", "saveToken", "token", "saveToken$app_debug", "Companion", "app_debug"})
public final class AppPreferences {
    private final android.content.SharedPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NAME = "AndroidPartyPrefs";
    private static final java.lang.String KEY_TOKEN = "appToken";
    public static final com.demo.androidparty.storage.preferences.AppPreferences.Companion Companion = null;
    
    public final void saveToken$app_debug(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getToken$app_debug() {
        return null;
    }
    
    public final void clear$app_debug() {
    }
    
    public AppPreferences(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/demo/androidparty/storage/preferences/AppPreferences$Companion;", "", "()V", "KEY_TOKEN", "", "NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}