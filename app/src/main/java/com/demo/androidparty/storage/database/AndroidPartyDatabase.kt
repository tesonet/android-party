package com.demo.androidparty.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.androidparty.dto.ServerModel

@Database(entities = [ServerModel::class], version = 1)
abstract class AndroidPartyDatabase : RoomDatabase() {

    abstract fun dao(): ServerDao

    companion object {
        const val DB_NAME = "AndroidPartyDatabase"
    }
}