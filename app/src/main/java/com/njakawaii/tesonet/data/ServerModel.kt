package com.njakawaii.tesonet.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ColumnInfo

@Entity(tableName = "servers")
data class ServerModel(
//        @PrimaryKey(autoGenerate = true) var id: Long?,
        @PrimaryKey var name: String,
        @ColumnInfo var distance: Int)