package com.simplekjl.domain.di

import com.simplekjl.domain.usecase.GetAllServersUseCase
import com.simplekjl.domain.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllServersUseCase(Dispatchers.IO, get()) }
    factory { LoginUseCase(Dispatchers.IO, get(), get()) }
}
