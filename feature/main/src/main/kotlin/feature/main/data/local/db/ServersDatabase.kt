package feature.main.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import feature.main.data.local.db.dao.ServersDao
import feature.main.data.local.db.model.ServerEntity

@Database(entities = [ServerEntity::class], version = 1, exportSchema = false)
abstract class ServersDatabase : RoomDatabase() {
    abstract fun serversDao(): ServersDao
}