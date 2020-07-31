package com.demo.androidparty.ui.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\u0011\u001a\u00020\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u000bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\r\u0010\t\u001a\u00020\u0015H\u0000\u00a2\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001a\u00020\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/demo/androidparty/ui/list/ServersListViewModel;", "Lcom/demo/androidparty/base/BaseViewModel;", "model", "Lcom/demo/androidparty/ui/list/ServerListModel;", "mapper", "Lcom/demo/androidparty/ui/list/mapper/ServerListMapper;", "internetStateProvider", "Lcom/demo/androidparty/utils/InternetStateProvider;", "(Lcom/demo/androidparty/ui/list/ServerListModel;Lcom/demo/androidparty/ui/list/mapper/ServerListMapper;Lcom/demo/androidparty/utils/InternetStateProvider;)V", "logout", "Landroidx/lifecycle/LiveData;", "", "getLogout$app_debug", "()Landroidx/lifecycle/LiveData;", "state", "Lcom/demo/androidparty/ui/list/ServerListFetchingState;", "getState$app_debug", "fetchLocalServerList", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchRemoteServerList", "fetchServersList", "Lkotlinx/coroutines/Job;", "logout$app_debug", "setServers", "servers", "", "Lcom/demo/androidparty/dto/ServerModel;", "app_debug"})
public final class ServersListViewModel extends com.demo.androidparty.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.demo.androidparty.ui.list.ServerListFetchingState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Unit> logout = null;
    private final com.demo.androidparty.ui.list.ServerListModel model = null;
    private final com.demo.androidparty.ui.list.mapper.ServerListMapper mapper = null;
    private final com.demo.androidparty.utils.InternetStateProvider internetStateProvider = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.demo.androidparty.ui.list.ServerListFetchingState> getState$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<kotlin.Unit> getLogout$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job logout$app_debug() {
        return null;
    }
    
    private final kotlinx.coroutines.Job fetchServersList() {
        return null;
    }
    
    private final void setServers(java.util.List<com.demo.androidparty.dto.ServerModel> servers) {
    }
    
    public ServersListViewModel(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.list.ServerListModel model, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.list.mapper.ServerListMapper mapper, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.utils.InternetStateProvider internetStateProvider) {
        super();
    }
}