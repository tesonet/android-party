package com.example.domainServer.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domainServer.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerDao {
    @Query(value = "select * from server_table")
    fun fetchAllServers(): Flow<List<ServerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertServers(servers: List<ServerEntity>): List<Long>
}
