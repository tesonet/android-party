package com.demo.androidparty.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H!\u00a2\u0006\u0002\b\u0007J\r\u0010\b\u001a\u00020\tH!\u00a2\u0006\u0002\b\nJ\r\u0010\u000b\u001a\u00020\fH!\u00a2\u0006\u0002\b\r\u00a8\u0006\u000f"}, d2 = {"Lcom/demo/androidparty/di/MainActivityModule;", "", "()V", "bindInternetStateProvider", "Lcom/demo/androidparty/utils/InternetStateProvider;", "provider", "Lcom/demo/androidparty/utils/InternetStateProviderImpl;", "bindInternetStateProvider$app_debug", "provideLoginFragment", "Lcom/demo/androidparty/ui/login/LoginFragment;", "provideLoginFragment$app_debug", "provideServersListFragment", "Lcom/demo/androidparty/ui/list/ServersListFragment;", "provideServersListFragment$app_debug", "Companion", "app_debug"})
@dagger.Module()
public abstract class MainActivityModule {
    public static final com.demo.androidparty.di.MainActivityModule.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector(modules = {com.demo.androidparty.di.module.LoginFragmentModule.class})
    @com.demo.androidparty.di.annotations.DaggerScope(value = com.demo.androidparty.ui.login.LoginFragment.class)
    public abstract com.demo.androidparty.ui.login.LoginFragment provideLoginFragment$app_debug();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.android.ContributesAndroidInjector(modules = {com.demo.androidparty.di.module.ServersListFragmentModule.class})
    @com.demo.androidparty.di.annotations.DaggerScope(value = com.demo.androidparty.ui.list.ServersListFragment.class)
    public abstract com.demo.androidparty.ui.list.ServersListFragment provideServersListFragment$app_debug();
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Binds()
    public abstract com.demo.androidparty.utils.InternetStateProvider bindInternetStateProvider$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.utils.InternetStateProviderImpl provider);
    
    private MainActivityModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.demo.androidparty.di.annotations.ViewModelKey(value = com.demo.androidparty.ui.main.MainViewModel.class)
    @dagger.multibindings.IntoMap()
    @dagger.Provides()
    public static final androidx.lifecycle.ViewModel provideMainViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.main.MainModel model) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.demo.androidparty.ui.main.MainViewModel provideMainViewModel$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.ui.main.MainActivity target, @org.jetbrains.annotations.NotNull()
    com.demo.androidparty.base.ViewModelFactory factory) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @dagger.Provides()
    public static final com.demo.androidparty.ui.main.MainModel provideMainModel$app_debug(@org.jetbrains.annotations.NotNull()
    com.demo.androidparty.storage.preferences.AppPreferences prefs) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u00a2\u0006\u0002\b\u0007J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0001\u00a2\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\u0001\u00a2\u0006\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/demo/androidparty/di/MainActivityModule$Companion;", "", "()V", "provideMainModel", "Lcom/demo/androidparty/ui/main/MainModel;", "prefs", "Lcom/demo/androidparty/storage/preferences/AppPreferences;", "provideMainModel$app_debug", "provideMainViewModel", "Lcom/demo/androidparty/ui/main/MainViewModel;", "target", "Lcom/demo/androidparty/ui/main/MainActivity;", "factory", "Lcom/demo/androidparty/base/ViewModelFactory;", "provideMainViewModel$app_debug", "provideMainViewModelFactory", "Landroidx/lifecycle/ViewModel;", "model", "provideMainViewModelFactory$app_debug", "app_debug"})
    @dagger.Module()
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @com.demo.androidparty.di.annotations.ViewModelKey(value = com.demo.androidparty.ui.main.MainViewModel.class)
        @dagger.multibindings.IntoMap()
        @dagger.Provides()
        public final androidx.lifecycle.ViewModel provideMainViewModelFactory$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.main.MainModel model) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        public final com.demo.androidparty.ui.main.MainViewModel provideMainViewModel$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.ui.main.MainActivity target, @org.jetbrains.annotations.NotNull()
        com.demo.androidparty.base.ViewModelFactory factory) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @dagger.Provides()
        public final com.demo.androidparty.ui.main.MainModel provideMainModel$app_debug(@org.jetbrains.annotations.NotNull()
        com.demo.androidparty.storage.preferences.AppPreferences prefs) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}