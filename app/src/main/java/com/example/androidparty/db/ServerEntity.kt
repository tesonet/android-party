package com.example.androidparty.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "server_table")
data class ServerEntity(
    @PrimaryKey(autoGenerate = true) val serverId: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "distance") val distance: Int
) : Serializable