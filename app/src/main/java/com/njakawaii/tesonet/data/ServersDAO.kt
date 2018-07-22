package com.njakawaii.tesonet.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ServersDAO {

    @Query("SELECT * from servers")
    fun getAll(): Flowable<List<ServerModel>>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: ServerModel)

    @Insert(onConflict = REPLACE)
    fun insertAll(pets: List<ServerModel>)

    @Query("DELETE from servers")
    fun deleteAll()
}
