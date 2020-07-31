package com.demo.androidparty.di.component;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/demo/androidparty/di/component/AppComponent;", "Ldagger/android/AndroidInjector;", "Lcom/demo/androidparty/AndroidPartyApp;", "Factory", "app_debug"})
@dagger.Component(modules = {dagger.android.support.AndroidSupportInjectionModule.class, com.demo.androidparty.di.module.AppModule.class, com.demo.androidparty.di.ActivityBindingModule.class, com.demo.androidparty.di.module.DispatcherModule.class, com.demo.androidparty.di.module.ApiModule.class, com.demo.androidparty.di.module.StorageModule.class})
@javax.inject.Singleton()
public abstract interface AppComponent extends dagger.android.AndroidInjector<com.demo.androidparty.AndroidPartyApp> {
    
    /**
     * AppComponent Builder interface. All implementation part is handled by a dagger compiler.
     */
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001\u00a8\u0006\u0003"}, d2 = {"Lcom/demo/androidparty/di/component/AppComponent$Factory;", "Ldagger/android/AndroidInjector$Factory;", "Lcom/demo/androidparty/AndroidPartyApp;", "app_debug"})
    @dagger.Component.Factory()
    public static abstract interface Factory extends dagger.android.AndroidInjector.Factory<com.demo.androidparty.AndroidPartyApp> {
    }
}