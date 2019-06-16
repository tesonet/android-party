package com.mariussavickas.android_party.persistance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class, ServerInfo::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun serverInfoDao(): ServerInfoDao
}