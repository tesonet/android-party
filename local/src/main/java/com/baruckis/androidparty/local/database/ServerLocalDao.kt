package com.baruckis.androidparty.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baruckis.androidparty.local.SERVERS_TABLE
import com.baruckis.androidparty.local.model.ServerLocal
import io.reactivex.Flowable

@Dao
interface ServerLocalDao {

    @Query("SELECT * FROM $SERVERS_TABLE")
    fun getServers(): Flowable<List<ServerLocal>>

    @Query("DELETE FROM $SERVERS_TABLE")
    fun deleteServers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveServers(serversList: List<ServerLocal>)

}