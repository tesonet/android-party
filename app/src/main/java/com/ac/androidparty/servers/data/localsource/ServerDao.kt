package com.ac.androidparty.servers.data.localsource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface ServerDao {
    @Query("SELECT * FROM serverentity")
    fun getAll(): List<ServerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(servers: List<ServerEntity>)

    @Query("DELETE FROM serverentity")
    fun dropAll()
}