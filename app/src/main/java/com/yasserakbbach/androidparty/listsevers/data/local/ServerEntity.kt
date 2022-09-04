package com.yasserakbbach.androidparty.listsevers.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "servers_table")
data class ServerEntity(
    @PrimaryKey(autoGenerate = false) val name: String,
    val distance: Int,
)