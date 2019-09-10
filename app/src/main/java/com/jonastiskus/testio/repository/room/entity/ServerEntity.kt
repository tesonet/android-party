package com.jonastiskus.testio.repository.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "server_table")
data class ServerEntity(@PrimaryKey @ColumnInfo(name = "response")val json : String)