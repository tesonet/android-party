package com.demo.androidparty.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005H\u0004J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005H\u0004J\u0012\u0010\u0007\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\b0\u0004H\u0004J%\u0010\t\u001a\u00020\b\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u0002H\u00050\u00042\u0006\u0010\n\u001a\u0002H\u0005H\u0004\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/demo/androidparty/base/BaseViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "createMutableLiveData", "Landroidx/lifecycle/LiveData;", "T", "createSingleLiveEvent", "call", "", "setValue", "value", "(Landroidx/lifecycle/LiveData;Ljava/lang/Object;)V", "app_debug"})
public abstract class BaseViewModel extends androidx.lifecycle.ViewModel {
    
    @org.jetbrains.annotations.NotNull()
    protected final <T extends java.lang.Object>androidx.lifecycle.LiveData<T> createMutableLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final <T extends java.lang.Object>androidx.lifecycle.LiveData<T> createSingleLiveEvent() {
        return null;
    }
    
    protected final <T extends java.lang.Object>void setValue(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<T> $this$setValue, T value) {
    }
    
    protected final void call(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<kotlin.Unit> $this$call) {
    }
    
    public BaseViewModel() {
        super();
    }
}