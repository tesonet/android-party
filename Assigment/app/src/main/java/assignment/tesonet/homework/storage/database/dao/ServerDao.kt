package assignment.tesonet.homework.storage.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert

import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.support.annotation.WorkerThread
import assignment.tesonet.homework.api.response.Server

@Dao
interface ServerDao {
    @WorkerThread
    @Insert(onConflict = REPLACE)
    @JvmSuppressWildcards
    fun save(list: List<Server>)

    @WorkerThread
    @Query("SELECT * FROM server")
    fun load(): List<Server>

    @WorkerThread
    @Query("DELETE FROM server")
    fun delete()
}