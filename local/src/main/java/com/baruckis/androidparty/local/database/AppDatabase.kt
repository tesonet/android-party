package com.baruckis.androidparty.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baruckis.androidparty.local.APP_DATABASE
import com.baruckis.androidparty.local.model.ServerLocal

@Database(entities = [ServerLocal::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun serverLocalDao(): ServerLocalDao

    // The AppDatabase a singleton to prevent having multiple instances of the database opened at the same time.
    companion object {

        // Marks the JVM backing field of the annotated property as volatile, meaning that writes to this field
        // are immediately made visible to other threads.
        @Volatile
        private var instance: AppDatabase? = null

        // For Singleton instantiation.
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Creates the database.
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, APP_DATABASE).build()
        }

    }

}