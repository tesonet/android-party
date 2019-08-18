package com.k4dima.party.app.data.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    fun preferences(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
}