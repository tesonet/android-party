package com.demo.androidparty.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\'\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/demo/androidparty/di/module/LoginFragmentModule;", "", "()V", "Companion", "app_debug"})
@dagger.Module()
public abstract class LoginFragmentModule {
    public static final com.demo.androidparty.di.module.LoginFragmentModule.Companion Companion = null;
    
    private LoginFragmentModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.ViewModelKey(value = com.demo.androidparty.ui.login.LoginViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Provides()
    public static final androidx.lifecycle.ViewModel provideLoginViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.login.LoginModel model, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.utils.InternetStateProvider internetStateProvider) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.demo.androidparty.ui.login.LoginViewModel provideLoginViewModel$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.login.LoginFragment target, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.base.ViewModelFactory factory) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.demo.androidparty.ui.login.LoginModel provideLoginModel$app_debug(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.IO()
    kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
    com.demo.androidparty.network.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.preferences.AppPreferences preferences) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0001\u00a2\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0001\u00a2\u0006\u0002\b\u0012J\u001d\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0001\u00a2\u0006\u0002\b\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/demo/androidparty/di/module/LoginFragmentModule$Companion;", "", "()V", "provideLoginModel", "Lcom/demo/androidparty/ui/login/LoginModel;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "apiService", "Lcom/demo/androidparty/network/ApiService;", "preferences", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "provideLoginModel$app_debug", "provideLoginViewModel", "Lcom/demo/androidparty/ui/login/LoginViewModel;", "target", "Lcom/demo/androidparty/ui/login/LoginFragment;", "factory", "Lcom/demo/androidparty/base/ViewModelFactory;", "provideLoginViewModel$app_debug", "provideLoginViewModelFactory", "Landroidx/lifecycle/ViewModel;", "model", "internetStateProvider", "Lcom/demo/androidparty/utils/InternetStateProvider;", "provideLoginViewModelFactory$app_debug", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.ViewModelKey(value = com.demo.androidparty.ui.login.LoginViewModel.class)
        @dagger.multibindings.IntoMap()
        @dagger.Provides()
        public final androidx.lifecycle.ViewModel provideLoginViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.login.LoginModel model, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.utils.InternetStateProvider internetStateProvider) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        public final com.demo.androidparty.ui.login.LoginViewModel provideLoginViewModel$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.login.LoginFragment target, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.base.ViewModelFactory factory) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        public final com.demo.androidparty.ui.login.LoginModel provideLoginModel$app_debug(@org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.IO()
        kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientUnauthorized()
        com.demo.androidparty.network.ApiService apiService, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.storage.preferences.AppPreferences preferences) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}