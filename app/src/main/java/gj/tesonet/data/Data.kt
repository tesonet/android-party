package gj.tesonet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gj.tesonet.data.dao.ServerDao
import gj.tesonet.data.model.Server

@Database(entities = [Server::class], version = 1)
abstract class Data : RoomDatabase() {

    abstract fun servers(): ServerDao

    companion object {

        fun create(context: Context): Data {
            return Room.databaseBuilder(
                context,
                Data::class.java,
                "database"
            ).build()
        }
    }
}
