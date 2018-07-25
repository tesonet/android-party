package com.teso.net.data_flow.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.teso.net.data_flow.database.entities.ServerEntity

@Dao
abstract class ServerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertServer(vararg user: ServerEntity)

    @Update
    abstract fun updateServer(vararg user: ServerEntity)

    @Delete
    abstract fun deleteServer(vararg user: ServerEntity)

    @Query("DELETE FROM server_table")
    abstract fun deleteAllServer()

    @Query("SELECT * FROM server_table")
    abstract fun getServerList(): LiveData<List<ServerEntity>>

    @Transaction
    @Query("SELECT * FROM server_table WHERE name = :currentName")
    abstract fun getOneServer(currentName: String): LiveData<ServerEntity>

    @Transaction
    open fun clearAndUpdateServer(listSites: List<ServerEntity>) {
        deleteAllServer()
        insertServer(*listSites.toTypedArray())
    }
}