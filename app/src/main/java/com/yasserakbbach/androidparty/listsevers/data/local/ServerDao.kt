package com.yasserakbbach.androidparty.listsevers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDao {

    @Query("SELECT * FROM servers_table")
    fun getServers(): Flow<List<ServerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServer(servers: List<ServerEntity>)

    @Query("DELETE FROM servers_table")
    suspend fun deleteAllServers()
}