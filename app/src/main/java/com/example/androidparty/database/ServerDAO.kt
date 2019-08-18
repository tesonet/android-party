package com.example.androidparty.database

import androidx.room.*

@Dao
interface ServerDAO {
    @Query("SELECT * FROM servers")
    fun getServers(): List<ServerEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateServer(server: ServerEntity)

    @Insert
    fun addServer(game: ServerEntity)

    @Delete
    fun deleteServer(game: ServerEntity)
}