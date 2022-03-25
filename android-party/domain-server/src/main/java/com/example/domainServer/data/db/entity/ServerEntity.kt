package com.example.domainServer.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domainServer.data.db.Constants.DISTANCE
import com.example.domainServer.data.db.Constants.SERVER_NAME
import com.example.domainServer.data.db.Constants.SERVER_TABLE

@Entity(tableName = SERVER_TABLE)
data class ServerEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = SERVER_NAME)
    val name: String,
    @ColumnInfo(name = DISTANCE)
    val distance: Int
)
