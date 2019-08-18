package com.example.androidparty.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "servers")
data class ServerEntity(
    @PrimaryKey @ColumnInfo(name = "serverName")val serverName: String,
    @ColumnInfo(name = "distance")val distance: Int
    )