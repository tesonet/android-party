package com.amaximan.tesonet.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.amaximan.tesonet.App
import com.amaximan.tesonet.model.database.server.Server
import com.amaximan.tesonet.model.database.server.ServerDao

@Database(entities = [Server::class], version = 1, exportSchema = false)
abstract class TesonetDB : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: TesonetDB? = null

        val instance: TesonetDB =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase().also { INSTANCE = it }
                }

        private fun buildDatabase() = Room.databaseBuilder(App.instance.applicationContext, TesonetDB::class.java, TesonetDB::class.java.simpleName).build()

    }

    abstract fun serverDao(): ServerDao
}