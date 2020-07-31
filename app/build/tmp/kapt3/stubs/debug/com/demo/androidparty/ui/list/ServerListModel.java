package com.demo.androidparty.ui.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\fH\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u000eJ\u001f\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0014H\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u000eJ!\u0010\u0016\u001a\u00020\f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0080@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/demo/androidparty/ui/list/ServerListModel;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "apiService", "Lcom/demo/androidparty/network/ApiService;", "preferences", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "dao", "Lcom/demo/androidparty/storage/database/ServerDao;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lcom/demo/androidparty/network/ApiService;Lcom/demo/androidparty/storage/preferences/AppPreferences;Lcom/demo/androidparty/storage/database/ServerDao;)V", "clearData", "", "clearData$app_debug", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDatabaseServersData", "", "Lcom/demo/androidparty/dto/ServerModel;", "fetchDatabaseServersData$app_debug", "fetchRemoteServersData", "Lcom/demo/androidparty/utils/NetworkResult;", "fetchRemoteServersData$app_debug", "saveServersData", "serversList", "saveServersData$app_debug", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ServerListModel {
    private final kotlinx.coroutines.CoroutineDispatcher dispatcher = null;
    private final com.demo.androidparty.network.ApiService apiService = null;
    private final com.demo.androidparty.storage.preferences.AppPreferences preferences = null;
    private final com.demo.androidparty.storage.database.ServerDao dao = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchRemoteServersData$app_debug(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.demo.androidparty.utils.NetworkResult<? extends java.util.List<com.demo.androidparty.dto.ServerModel>>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchDatabaseServersData$app_debug(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.demo.androidparty.dto.ServerModel>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveServersData$app_debug(@org.jetbrains.annotations.NotNull()
    java.util.List<com.demo.androidparty.dto.ServerModel> serversList, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearData$app_debug(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0) {
        return null;
    }
    
    public ServerListModel(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.IO()
    kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.network.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.preferences.AppPreferences preferences, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.database.ServerDao dao) {
        super();
    }
}