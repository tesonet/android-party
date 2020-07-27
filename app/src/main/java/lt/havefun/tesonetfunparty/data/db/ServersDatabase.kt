package lt.havefun.tesonetfunparty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import lt.havefun.tesonetfunparty.data.Server

@Database(entities = [Server::class], version = 1, exportSchema = false)
abstract class ServersDatabase : RoomDatabase() {
    abstract fun getCachedServers(): ServersDao
}
