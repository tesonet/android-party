package com.demo.androidparty.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\'\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/demo/androidparty/di/module/AppModule;", "", "()V", "Companion", "app_debug"})
@dagger.Module()
public abstract class AppModule {
    public static final com.demo.androidparty.di.module.AppModule.Companion Companion = null;
    
    private AppModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.AppContext()
    @dagger.Provides()
    public static final android.content.Context provideApplicationContext$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.AndroidPartyApp instance) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/demo/androidparty/di/module/AppModule$Companion;", "", "()V", "provideApplicationContext", "Landroid/content/Context;", "instance", "Lcom/demo/androidparty/AndroidPartyApp;", "provideApplicationContext$app_debug", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.AppContext()
        @dagger.Provides()
        public final android.content.Context provideApplicationContext$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.AndroidPartyApp instance) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}