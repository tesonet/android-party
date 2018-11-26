package com.tesonet.testio.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Server(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val distance: String
)
