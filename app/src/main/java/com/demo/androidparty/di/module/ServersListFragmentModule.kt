package com.demo.androidparty.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.demo.androidparty.base.ViewModelFactory
import com.demo.androidparty.di.annotations.HttpClientAuthorized
import com.demo.androidparty.di.annotations.IO
import com.demo.androidparty.di.annotations.ViewModelKey
import com.demo.androidparty.network.ApiService
import com.demo.androidparty.storage.database.ServerDao
import com.demo.androidparty.storage.preferences.AppPreferences
import com.demo.androidparty.ui.list.ServerListModel
import com.demo.androidparty.ui.list.ServersListFragment
import com.demo.androidparty.ui.list.ServersListViewModel
import com.demo.androidparty.ui.list.mapper.ServerListMapper
import com.demo.androidparty.ui.list.mapper.ServerListMapperImpl
import com.demo.androidparty.utils.InternetStateProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher

@Module
abstract class ServersListFragmentModule private constructor() {

    @Binds
    internal abstract fun bindServerListMapper(mapper: ServerListMapperImpl): ServerListMapper

    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(ServersListViewModel::class)
        internal fun provideServerListViewModelFactory(
            model: ServerListModel,
            mapper: ServerListMapper,
            internetStateProvider: InternetStateProvider
        ): ViewModel = ServersListViewModel(model, mapper, internetStateProvider)

        @JvmStatic
        @Provides
        internal fun provideServerListViewModel(
            target: ServersListFragment,
            factory: ViewModelFactory
        ): ServersListViewModel = ViewModelProviders.of(target, factory).get(
            ServersListViewModel::class.java
        )

        @JvmStatic
        @Provides
        internal fun provideServerListModel(
            @IO dispatcher: CoroutineDispatcher,
            @HttpClientAuthorized apiService: ApiService,
            preferences: AppPreferences,
            dao: ServerDao
        ): ServerListModel = ServerListModel(dispatcher, apiService, preferences, dao)
    }
}