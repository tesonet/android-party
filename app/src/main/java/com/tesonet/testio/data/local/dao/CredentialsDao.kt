package com.tesonet.testio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tesonet.testio.data.local.entity.Credentials
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Dao
interface CredentialsDao {
    @Query("SELECT * FROM credentials LIMIT 1")
    fun select(): Credentials?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(credentials: Credentials)

    @Query("DELETE FROM credentials")
    fun delete()
}

suspend fun CredentialsDao.selectAsync()
        = GlobalScope.async { select() }.await()

suspend fun CredentialsDao.insertAsync(credentials: Credentials)
        = GlobalScope.async { insert(credentials) }.await()

suspend fun CredentialsDao.deleteAsync()
        = GlobalScope.async { delete() }.await()