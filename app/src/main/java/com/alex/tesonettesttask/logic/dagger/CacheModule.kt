package com.alex.tesonettesttask.logic.dagger

import com.alex.tesonettesttask.logic.utils.Preferences
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()

    @Singleton
    @Provides
    fun providePreferences(): Preferences = Preferences

}