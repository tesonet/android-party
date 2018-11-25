package com.tesonet.testio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tesonet.testio.data.local.dao.CredentialsDao
import com.tesonet.testio.data.local.dao.ServerDao
import com.tesonet.testio.data.local.dao.TokenDao
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.data.local.entity.Server
import com.tesonet.testio.data.local.entity.Token

@Database(
    entities = [Credentials::class, Token::class, Server::class],
    version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun credentialsDao(): CredentialsDao
    abstract fun tokenDao(): TokenDao
    abstract fun serverDao(): ServerDao
}