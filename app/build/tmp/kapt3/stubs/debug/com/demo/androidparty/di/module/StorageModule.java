package com.demo.androidparty.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\'\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/demo/androidparty/di/module/StorageModule;", "", "()V", "Companion", "app_debug"})
@dagger.Module()
public abstract class StorageModule {
    public static final com.demo.androidparty.di.module.StorageModule.Companion Companion = null;
    
    private StorageModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final com.demo.androidparty.storage.preferences.AppPreferences provideSharedPreferencesManager$app_debug(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences preferences) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final android.content.SharedPreferences provideDefaultSharedPreferences$app_debug(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.AppContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final com.demo.androidparty.storage.database.AndroidPartyDatabase provideAndroidPartyDatabase$app_debug(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.AppContext()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public static final com.demo.androidparty.storage.database.ServerDao provideAndroidPartyDatabaseDao$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.database.AndroidPartyDatabase database) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0001\u00a2\u0006\u0002\b\u000bJ\u0017\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0001\u00a2\u0006\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/demo/androidparty/di/module/StorageModule$Companion;", "", "()V", "provideAndroidPartyDatabase", "Lcom/demo/androidparty/storage/database/AndroidPartyDatabase;", "context", "Landroid/content/Context;", "provideAndroidPartyDatabase$app_debug", "provideAndroidPartyDatabaseDao", "Lcom/demo/androidparty/storage/database/ServerDao;", "database", "provideAndroidPartyDatabaseDao$app_debug", "provideDefaultSharedPreferences", "Landroid/content/SharedPreferences;", "provideDefaultSharedPreferences$app_debug", "provideSharedPreferencesManager", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "preferences", "provideSharedPreferencesManager$app_debug", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final com.demo.androidparty.storage.preferences.AppPreferences provideSharedPreferencesManager$app_debug(@org.jetbrains.annotations.NotNull()
        android.content.SharedPreferences preferences) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final android.content.SharedPreferences provideDefaultSharedPreferences$app_debug(@org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.AppContext()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final com.demo.androidparty.storage.database.AndroidPartyDatabase provideAndroidPartyDatabase$app_debug(@org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.AppContext()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @javax.inject.Singleton()
        @dagger.Provides()
        public final com.demo.androidparty.storage.database.ServerDao provideAndroidPartyDatabaseDao$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.storage.database.AndroidPartyDatabase database) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}