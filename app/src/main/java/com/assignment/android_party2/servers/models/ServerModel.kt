package com.assignment.android_party2.servers.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Servers")
data class ServerModel(
    @PrimaryKey
    val name: String,
    val distance: Int
)