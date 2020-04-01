package com.assignment.android_party2

import android.app.Application
import androidx.room.Room
import com.assignment.android_party2.servers.db.ServersDatabase

class TestioApplication : Application() {

    companion object {
        var database: ServersDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(applicationContext, ServersDatabase::class.java, "servers_db").fallbackToDestructiveMigration().build()
    }
}