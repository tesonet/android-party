package com.example.android_party.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Server(
        @PrimaryKey
        val name: String,
        val distance: String
)