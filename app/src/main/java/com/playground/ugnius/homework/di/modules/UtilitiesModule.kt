package com.playground.ugnius.homework.di.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class UtilitiesModule() {

    @Provides
    fun provideRealm() = Realm.getDefaultInstance()
}