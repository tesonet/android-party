package assignment.tesonet.homework.storage.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import assignment.tesonet.homework.api.response.Server
import assignment.tesonet.homework.storage.database.dao.ServerDao

@Database(entities = [Server::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun serverDao(): ServerDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MyDatabase::class.java, "myDatabase.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}