package com.example.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OtherInterceptorOkHttpClient
