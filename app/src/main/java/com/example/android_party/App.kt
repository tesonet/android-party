package com.example.android_party

import android.app.Application
import androidx.room.Room
import com.example.android_party.data.AppDatabase

class App : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "testio_db")
            . build()
    }

}