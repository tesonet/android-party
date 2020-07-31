package com.demo.androidparty.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.demo.androidparty.base.ViewModelFactory
import com.demo.androidparty.di.annotations.HttpClientUnauthorized
import com.demo.androidparty.di.annotations.IO
import com.demo.androidparty.di.annotations.ViewModelKey
import com.demo.androidparty.network.ApiService
import com.demo.androidparty.storage.preferences.AppPreferences
import com.demo.androidparty.ui.login.LoginFragment
import com.demo.androidparty.ui.login.LoginModel
import com.demo.androidparty.ui.login.LoginViewModel
import com.demo.androidparty.utils.InternetStateProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher

@Module
abstract class LoginFragmentModule private constructor() {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        internal fun provideLoginViewModelFactory(
            model: LoginModel,
            internetStateProvider: InternetStateProvider
        ): ViewModel = LoginViewModel(model, internetStateProvider)

        @JvmStatic
        @Provides
        internal fun provideLoginViewModel(
            target: LoginFragment,
            factory: ViewModelFactory
        ): LoginViewModel = ViewModelProviders.of(target, factory).get(LoginViewModel::class.java)

        @JvmStatic
        @Provides
        internal fun provideLoginModel(
            @IO dispatcher: CoroutineDispatcher,
            @HttpClientUnauthorized apiService: ApiService,
            preferences: AppPreferences
        ): LoginModel = LoginModel(dispatcher, apiService, preferences)
    }
}