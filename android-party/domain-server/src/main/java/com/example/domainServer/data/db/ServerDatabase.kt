package com.example.domainServer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domainServer.data.db.dao.ServerDao
import com.example.domainServer.data.db.entity.ServerEntity

@Database(
    entities = [ServerEntity::class],
    version = 1,
    exportSchema = false,
)

abstract class ServerDatabase : RoomDatabase() {
    abstract fun launchDao(): ServerDao
}
