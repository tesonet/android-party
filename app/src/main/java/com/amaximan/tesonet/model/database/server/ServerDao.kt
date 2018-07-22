package com.amaximan.tesonet.model.database.server

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.amaximan.tesonet.model.database.base.BaseDao

@Dao
abstract class ServerDao : BaseDao<Server> {
    @Query("SELECT * FROM Server")
    abstract fun getAll(): LiveData<List<Server>>

    @Query("DELETE FROM Server")
    abstract fun deleteAll()
}