package com.jonastiskus.testio.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonastiskus.testio.repository.room.entity.ServerEntity

@Dao
interface ServerDao {

    @Query("SELECT * from server_table")
    fun getAll(): LiveData<ServerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: ServerEntity)

    @Query("DELETE FROM server_table")
    fun deleteAll()

}