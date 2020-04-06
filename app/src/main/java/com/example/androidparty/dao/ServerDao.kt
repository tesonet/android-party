package com.example.androidparty.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidparty.db.ServerEntity
import io.reactivex.Observable

@Dao
interface ServerDao {

    @Query("SELECT * FROM server_table")
    fun getAll(): Observable<List<ServerEntity>>

    @Insert
    fun insertAll(data: List<ServerEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(serverList: List<ServerEntity>): List<Long>

    @Query("DELETE FROM server_table")
    fun deleteAllServers()

}