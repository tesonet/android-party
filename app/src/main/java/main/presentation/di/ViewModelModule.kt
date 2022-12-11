package main.presentation.di

import androidx.lifecycle.ViewModel
import app.presentation.di.ViewModelKey
import main.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(repoViewModel: MainViewModel): ViewModel
}