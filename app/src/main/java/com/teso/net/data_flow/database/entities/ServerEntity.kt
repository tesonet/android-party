package com.teso.net.data_flow.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "server_table")
data class ServerEntity(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "distance")
        val distance: Int
)