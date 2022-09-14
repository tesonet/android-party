package com.simplekjl.data.di

import com.simplekjl.data.repository.NetworkSource
import com.simplekjl.data.repository.NetworkSourceImpl
import com.simplekjl.data.repository.ServersRepositoryImpl
import com.simplekjl.domain.repository.ServersRepository
import org.koin.dsl.module

val dataModule = createDataModule()

private fun createDataModule() = module {
    factory<NetworkSource> { NetworkSourceImpl(get()) }
    factory<ServersRepository> { ServersRepositoryImpl(get()) }
}
