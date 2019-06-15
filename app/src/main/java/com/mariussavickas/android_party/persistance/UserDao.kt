package com.mariussavickas.android_party.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username = :username")
    fun getUser(username: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)
}