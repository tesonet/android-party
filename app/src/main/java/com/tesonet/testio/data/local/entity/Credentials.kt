package com.tesonet.testio.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Credentials(
    @ColumnInfo val username: String,
    @ColumnInfo val password: String,
    @PrimaryKey val id: Int = 1
)
