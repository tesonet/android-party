package com.demo.androidparty.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/demo/androidparty/di/module/DispatcherModule;", "", "()V", "Companion", "app_debug"})
@dagger.Module()
public final class DispatcherModule {
    public static final com.demo.androidparty.di.module.DispatcherModule.Companion Companion = null;
    
    public DispatcherModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.IO()
    @dagger.Provides()
    public static final kotlinx.coroutines.CoroutineDispatcher provideIODispatcher$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.Main()
    @dagger.Provides()
    public static final kotlinx.coroutines.CoroutineDispatcher provideMainDispatcher$app_debug() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\r\u0010\u0003\u001a\u00020\u0004H\u0001\u00a2\u0006\u0002\b\u0005J\r\u0010\u0006\u001a\u00020\u0004H\u0001\u00a2\u0006\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/demo/androidparty/di/module/DispatcherModule$Companion;", "", "()V", "provideIODispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "provideIODispatcher$app_debug", "provideMainDispatcher", "provideMainDispatcher$app_debug", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.IO()
        @dagger.Provides()
        public final kotlinx.coroutines.CoroutineDispatcher provideIODispatcher$app_debug() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.Main()
        @dagger.Provides()
        public final kotlinx.coroutines.CoroutineDispatcher provideMainDispatcher$app_debug() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}