package com.example.android_party.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ServersDao {
    @get:Query("SELECT * FROM server")
    val getAll: List<Server>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(servers: List<Server>)

    @Query("DELETE FROM server")
    fun delete()
}