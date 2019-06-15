package com.mariussavickas.android_party.persistance

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey var username: String,
    @Ignore val password: String?,
    var token: String?
) {
    constructor() : this("", null, null)
}
