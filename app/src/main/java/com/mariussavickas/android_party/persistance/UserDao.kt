package com.mariussavickas.android_party.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Query("DELETE from user")
    fun clear()

}