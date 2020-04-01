package com.assignment.android_party2.servers.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.android_party2.servers.models.ServerModel

@Dao
interface ServerDao {
    @Query("SELECT * FROM Servers")
    fun getServers() : LiveData<List<ServerModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertServers(serversList: List<ServerModel>)

    @Query("DELETE FROM Servers")
    fun deleteServers()
}