package com.mariussavickas.android_party.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface ServerInfoDao {
    @Query("SELECT * FROM serverInfo")
    fun getServerInfoAll(): Flowable<List<ServerInfo>>

    @Insert
    fun insert(serverInfo: List<ServerInfo>)

    @Query("DELETE FROM serverInfo")
    fun clear()
}