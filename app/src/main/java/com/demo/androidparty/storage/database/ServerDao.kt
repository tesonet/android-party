package com.demo.androidparty.storage.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.androidparty.dto.ServerModel

@Dao
interface ServerDao {
    @Query(
        """
            SELECT 
                * 
            FROM 
                `ServerModel`
        """
    )
    suspend fun getAll(): List<ServerModel>

    @Query(
        """
            DELETE FROM `ServerModel`
        """
    )
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(servers: List<ServerModel>)
}