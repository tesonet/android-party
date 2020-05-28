package com.baruckis.androidparty.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.baruckis.androidparty.local.SERVERS_TABLE

@Entity(tableName = SERVERS_TABLE)
data class ServerLocal(

    @PrimaryKey(autoGenerate = true)
    val serverId: Long = 0,

    var name: String,
    var distance: Int
) {
}