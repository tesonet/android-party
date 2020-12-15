package com.example.android_party.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Server::class, Token::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun serversDao(): ServersDao
    abstract fun tokenDao(): TokenDao
}