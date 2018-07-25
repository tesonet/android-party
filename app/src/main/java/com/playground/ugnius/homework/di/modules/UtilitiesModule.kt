package com.playground.ugnius.homework.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import dagger.Module
import dagger.Provides

@Module
class UtilitiesModule(private val context: Context) {

    @Provides
    fun providePreferences(): SharedPreferences {
        return context.getSharedPreferences("preferences", MODE_PRIVATE)
    }
}