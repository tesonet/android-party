package com.example.android_party.data

import androidx.room.*

@Dao
interface TokenDao {
    @Query("SELECT * FROM Token")
    fun get(): Token

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(token: Token)

    @Query("DELETE FROM Token")
    fun delete()
}