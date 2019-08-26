package com.example.androidparty.dagger

import com.example.androidparty.repository.Repository
import com.example.androidparty.viewmodel.LoginViewModel
import com.example.androidparty.viewmodel.ServersListViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {
    @Provides
    fun provideServersListViewModel(repository: Repository): ServersListViewModel = ServersListViewModel(repository)

    @Provides
    fun provideLoginViewModel(repository: Repository): LoginViewModel = LoginViewModel(repository)
}