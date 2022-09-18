package com.ac.androidparty.servers.data.localsource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ServerEntity::class], version = 1)
internal abstract class ServersDatabase: RoomDatabase(){
    abstract fun serversDao(): ServerDao
}