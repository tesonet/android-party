package com.tesonet.testio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tesonet.testio.data.local.entity.Token
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@Dao
interface TokenDao {
    @Query("SELECT * FROM token LIMIT 1")
    fun select(): Token?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(token: Token)
}

suspend fun TokenDao.selectAsync()
        = GlobalScope.async { select() }.await()

suspend fun TokenDao.insertAsync(token: Token)
        = GlobalScope.async { insert(token) }.await()