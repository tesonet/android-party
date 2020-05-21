package com.baruckis.androidparty.ui.di

import com.baruckis.androidparty.data.repository.MainRepositoryImpl
import com.baruckis.androidparty.domain.repository.MainRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: MainRepositoryImpl): MainRepository

}