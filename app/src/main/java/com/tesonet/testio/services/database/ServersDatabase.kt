package com.tesonet.testio.services.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tesonet.testio.services.data.server.Server

@Database(entities = [Server::class], version = 1, exportSchema = false)
abstract class ServersDatabase : RoomDatabase() {
    abstract fun serversDao(): ServersDao
}