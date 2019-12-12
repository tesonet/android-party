package com.giedrius.androidparty.task.dependency

import com.giedrius.androidparty.task.data.Repository
import com.giedrius.androidparty.task.viewmodel.LoginViewModel
import com.giedrius.androidparty.task.viewmodel.ServersListViewModel
import dagger.Module
import dagger.Provides

@Module
class ApplicationViewModelsModule {
    @Provides fun provideServersListModel(repository: Repository): ServersListViewModel = ServersListViewModel(repository)
    @Provides fun provideLoginModel(repository: Repository): LoginViewModel = LoginViewModel(repository)
}