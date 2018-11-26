package com.tesonet.testio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tesonet.testio.data.local.entity.Server
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Dao
interface ServerDao {
    @Query("DELETE FROM server")
    fun deleteAll()

    @Query("SELECT * FROM server")
    fun selectAll(): List<Server>

    @Insert
    fun insertMany(servers: List<Server>)
}

suspend fun ServerDao.deleteAllAsync() =
        GlobalScope.async { deleteAll() }.await()

suspend fun ServerDao.selectAllAsync() =
        GlobalScope.async { selectAll() }.await()

suspend fun ServerDao.insertManyAsync(servers: List<Server>) =
        GlobalScope.async { insertMany(servers) }.await()