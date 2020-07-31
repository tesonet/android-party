package com.demo.androidparty.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/demo/androidparty/utils/InternetStateProviderImpl;", "Lcom/demo/androidparty/utils/InternetStateProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isOnline", "", "app_debug"})
public final class InternetStateProviderImpl implements com.demo.androidparty.utils.InternetStateProvider {
    private final android.content.Context context = null;
    
    @java.lang.Override()
    public boolean isOnline() {
        return false;
    }
    
    @javax.inject.Inject()
    public InternetStateProviderImpl(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.AppContext()
    android.content.Context context) {
        super();
    }
}