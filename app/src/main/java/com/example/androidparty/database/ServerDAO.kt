package com.example.androidparty.database

import androidx.room.*

@Dao
interface ServerDAO {
    @Query("SELECT * FROM servers")
    fun getServers(): List<ServerEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateServer(server: ServerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addServer(server: ServerEntity)

    @Delete
    fun deleteServer(server: ServerEntity)
}