/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.ui.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baruckis.androidparty.presentation.CoroutineContextProvider
import com.baruckis.androidparty.presentation.launcher.LauncherViewModel
import com.baruckis.androidparty.presentation.login.LoginViewModel
import com.baruckis.androidparty.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * Will be responsible for providing ViewModels.
 */
@Module
abstract class PresentationModule {

    companion object {
        @Provides
        fun provideCoroutineContextProvider(): CoroutineContextProvider = CoroutineContextProvider()
    }

    // We'd like to take this implementation of the ViewModel class and make it available
    // in an injectable map with LoginViewModel::class as a key to that map.
    @Binds
    @IntoMap
    // We use a restriction on multibound map defined with @ViewModelKey annotation,
    // and if don't need any, we should use @ClassKey annotation provided by Dagger.
    @ViewModelKey(LauncherViewModel::class)
    abstract fun bindLauncherViewModel(launcherViewModel: LauncherViewModel): ViewModel

    // We'd like to take this implementation of the ViewModel class and make it available
    // in an injectable map with LoginViewModel::class as a key to that map.
    @Binds
    @IntoMap
    // We use a restriction on multibound map defined with @ViewModelKey annotation,
    // and if don't need any, we should use @ClassKey annotation provided by Dagger.
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}