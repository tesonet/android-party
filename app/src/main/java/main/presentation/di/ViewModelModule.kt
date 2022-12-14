package main.presentation.di

import androidx.lifecycle.ViewModel
import app.presentation.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoMap
import main.presentation.MainViewModel

@InstallIn(ActivityRetainedComponent::class)
@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(repoViewModel: MainViewModel): ViewModel
}