package com.example.alex.partyapp.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.alex.partyapp.data.ServersRepository;
import com.example.alex.partyapp.data.ServersRepositoryImpl;
import com.example.alex.partyapp.data.ServersStorage;
import com.example.alex.partyapp.data.TokenStorage;
import com.example.alex.partyapp.network.TesonetApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    TokenStorage provideLoginStorage(SharedPreferences preferences) {
        return new TokenStorage(preferences);
    }

    @Provides
    @Singleton
    ServersStorage provideServersStorage(SharedPreferences preferences) {
        return new ServersStorage(preferences);
    }

    @Provides
    @Singleton
    ServersRepository provideServersRepository(TesonetApi api, TokenStorage tokenStorage, ServersStorage serversStorage) {
        return new ServersRepositoryImpl(api, tokenStorage, serversStorage);
    }
}
