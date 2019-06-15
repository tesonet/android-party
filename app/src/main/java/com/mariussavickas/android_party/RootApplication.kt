package com.mariussavickas.android_party

import android.app.Application
import androidx.room.Room
import com.mariussavickas.android_party.persistance.AppDatabase


class RootApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "partyDb"
        ).build()
    }

    companion object {
        lateinit var appDatabase: AppDatabase
    }
}