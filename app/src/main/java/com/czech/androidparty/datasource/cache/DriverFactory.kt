package com.czech.androidparty.datasource.cache

import android.content.Context
import com.czech.androidparty.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

class DriverFactory(private val context: Context) {

    fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "androidParty.db")
    }
}