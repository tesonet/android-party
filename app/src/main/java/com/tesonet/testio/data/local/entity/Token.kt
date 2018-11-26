package com.tesonet.testio.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Token(
    @ColumnInfo val token: String,
    @ColumnInfo val issuedAt: Long = Date().time,
    @PrimaryKey val id: Int = 1
) {
    // TODO: Check token expiration when it's known
    fun isExpired() = true
}
