package com.example.androidparty.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import javax.inject.Singleton


const val DATABASE_NAME = "Serverlist.db"

@Singleton
@Database(entities = [ServerEntity::class], version = 1, exportSchema = false)
abstract class ServersDB: RoomDatabase() {

    companion object {
        private var instance: ServersDB? = null
        var TEST_MODE = false

        fun getInstance(appContext: Context): ServersDB {
            if (instance == null) {
                if(TEST_MODE){
                    instance =
                        Room.inMemoryDatabaseBuilder(appContext, ServersDB::class.java).allowMainThreadQueries().build()
                }
                else {
                    instance =
                        Room.databaseBuilder(appContext.applicationContext, ServersDB::class.java, DATABASE_NAME)
                            .fallbackToDestructiveMigration().build()
                } }

            return instance!!
        }
    }
    abstract fun serverDao(): ServerDAO
}