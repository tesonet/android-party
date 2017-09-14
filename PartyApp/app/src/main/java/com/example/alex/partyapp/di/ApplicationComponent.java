package com.example.alex.partyapp.di;

import com.example.alex.partyapp.MainActivity;
import com.example.alex.partyapp.loading.LoadingFragment;
import com.example.alex.partyapp.login.LoginFragment;
import com.example.alex.partyapp.servers.ServersFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(LoginFragment fragment);
    void inject(ServersFragment fragment);
    void inject(MainActivity activity);
    void inject(LoadingFragment fragment);
}
