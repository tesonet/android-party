package com.mariussavickas.android_party.persistance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ServerInfo(
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "distance") var distance: Int?
) {
    @PrimaryKey(autoGenerate = true) var uid: Int = 0
}