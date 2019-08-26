package com.example.androidparty.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "servers")
data class ServerEntity(
    @PrimaryKey @NonNull val serverName: String,
    val distance: Int
    )