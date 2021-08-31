package com.tesonet.testio.service.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tesonet.testio.service.data.server.Server
import io.reactivex.Single

@Dao
interface ServersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllServers(servers: List<Server>)

    @Query("SELECT * FROM server_table")
    fun readAllServers(): Single<List<Server>>

    @Query("DELETE FROM server_table")
    fun deleteAllServers()
}