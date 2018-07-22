package com.amaximan.tesonet.model.database.base

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(obj: List<T>)
}