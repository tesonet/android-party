package com.demo.androidparty.ui.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J!\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0000\u00a2\u0006\u0002\b\u0013R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/demo/androidparty/ui/login/LoginViewModel;", "Lcom/demo/androidparty/base/BaseViewModel;", "model", "Lcom/demo/androidparty/ui/login/LoginModel;", "internetStateProvider", "Lcom/demo/androidparty/utils/InternetStateProvider;", "(Lcom/demo/androidparty/ui/login/LoginModel;Lcom/demo/androidparty/utils/InternetStateProvider;)V", "error", "Landroidx/lifecycle/LiveData;", "", "getError$app_debug", "()Landroidx/lifecycle/LiveData;", "loggedIn", "", "getLoggedIn$app_debug", "login", "Lkotlinx/coroutines/Job;", "username", "password", "login$app_debug", "app_debug"})
public final class LoginViewModel extends com.demo.androidparty.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> loggedIn = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> error = null;
    private final com.demo.androidparty.ui.login.LoginModel model = null;
    private final com.demo.androidparty.utils.InternetStateProvider internetStateProvider = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getLoggedIn$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getError$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job login$app_debug(@org.jetbrains.annotations.Nullable()
    java.lang.String username, @org.jetbrains.annotations.Nullable()
    java.lang.String password) {
        return null;
    }
    
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.login.LoginModel model, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.utils.InternetStateProvider internetStateProvider) {
        super();
    }
}