package com.demo.androidparty.dto

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class ServerModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val distance: Int
)