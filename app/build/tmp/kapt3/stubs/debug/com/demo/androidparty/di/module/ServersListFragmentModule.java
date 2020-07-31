package com.demo.androidparty.di.module;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!\u00a2\u0006\u0002\b\u0007\u00a8\u0006\t"}, d2 = {"Lcom/demo/androidparty/di/module/ServersListFragmentModule;", "", "()V", "bindServerListMapper", "Lcom/demo/androidparty/ui/list/mapper/ServerListMapper;", "mapper", "Lcom/demo/androidparty/ui/list/mapper/ServerListMapperImpl;", "bindServerListMapper$app_debug", "Companion", "app_debug"})
@dagger.Module()
public abstract class ServersListFragmentModule {
    public static final com.demo.androidparty.di.module.ServersListFragmentModule.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.demo.androidparty.ui.list.mapper.ServerListMapper bindServerListMapper$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.list.mapper.ServerListMapperImpl mapper);
    
    private ServersListFragmentModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.ViewModelKey(value = com.demo.androidparty.ui.list.ServersListViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Provides()
    public static final androidx.lifecycle.ViewModel provideServerListViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.list.ServerListModel model, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.list.mapper.ServerListMapper mapper, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.utils.InternetStateProvider internetStateProvider) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.demo.androidparty.ui.list.ServersListViewModel provideServerListViewModel$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.list.ServersListFragment target, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.base.ViewModelFactory factory) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.demo.androidparty.ui.list.ServerListModel provideServerListModel$app_debug(@org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.IO()
    kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.HttpClientAuthorized()
    com.demo.androidparty.network.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.preferences.AppPreferences preferences, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.database.ServerDao dao) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001\u00a2\u0006\u0002\b\rJ\u001d\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0001\u00a2\u0006\u0002\b\u0014J%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001\u00a2\u0006\u0002\b\u001c\u00a8\u0006\u001d"}, d2 = {"Lcom/demo/androidparty/di/module/ServersListFragmentModule$Companion;", "", "()V", "provideServerListModel", "Lcom/demo/androidparty/ui/list/ServerListModel;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "apiService", "Lcom/demo/androidparty/network/ApiService;", "preferences", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "dao", "Lcom/demo/androidparty/storage/database/ServerDao;", "provideServerListModel$app_debug", "provideServerListViewModel", "Lcom/demo/androidparty/ui/list/ServersListViewModel;", "target", "Lcom/demo/androidparty/ui/list/ServersListFragment;", "factory", "Lcom/demo/androidparty/base/ViewModelFactory;", "provideServerListViewModel$app_debug", "provideServerListViewModelFactory", "Landroidx/lifecycle/ViewModel;", "model", "mapper", "Lcom/demo/androidparty/ui/list/mapper/ServerListMapper;", "internetStateProvider", "Lcom/demo/androidparty/utils/InternetStateProvider;", "provideServerListViewModelFactory$app_debug", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.ViewModelKey(value = com.demo.androidparty.ui.list.ServersListViewModel.class)
        @dagger.multibindings.IntoMap()
        @dagger.Provides()
        public final androidx.lifecycle.ViewModel provideServerListViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.list.ServerListModel model, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.list.mapper.ServerListMapper mapper, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.utils.InternetStateProvider internetStateProvider) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        public final com.demo.androidparty.ui.list.ServersListViewModel provideServerListViewModel$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.list.ServersListFragment target, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.base.ViewModelFactory factory) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        public final com.demo.androidparty.ui.list.ServerListModel provideServerListModel$app_debug(@org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.IO()
        kotlinx.coroutines.CoroutineDispatcher dispatcher, @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.HttpClientAuthorized()
        com.demo.androidparty.network.ApiService apiService, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.storage.preferences.AppPreferences preferences, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.storage.database.ServerDao dao) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}