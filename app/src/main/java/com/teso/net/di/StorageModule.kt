package com.teso.net.di

import android.arch.persistence.room.Room
import android.content.Context
import android.text.SpannableStringBuilder
import com.commonsware.cwac.saferoom.SafeHelperFactory
import com.teso.net.BuildConfig
import com.teso.net.data_flow.IUserStorage
import com.teso.net.data_flow.PreferenceUserStorage
import com.teso.net.data_flow.database.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module()
class StorageModule {

    @Provides
    @Singleton
    fun provideUserStorage(context: Context): IUserStorage = PreferenceUserStorage(context)


    @Provides
    @Singleton
    fun provideDB(context: Context): LocalDatabase = Room.databaseBuilder(context, LocalDatabase::class.java, "teso_net")
            .openHelperFactory(if (BuildConfig.DEBUG) null else SafeHelperFactory.fromUser(SpannableStringBuilder("d28fy84hbgcbr8")))
            .fallbackToDestructiveMigration()
            .build()
}