package gj.tesonet.data.dao

import androidx.room.*
import gj.tesonet.data.model.Server

@Dao
interface ServerDao {

    @Query("SELECT count(*) FROM server")
    suspend fun count(): Long

    @Query("SELECT * FROM server")
    suspend fun getAll(): List<Server>

    @Insert
    suspend fun insertAll(vararg servers: Server)

    @Query("DELETE FROM server")
    suspend fun deleteAll()

    @Transaction
    open suspend fun setAll(servers: List<Server>) {
        deleteAll()
        insertAll(*servers.toTypedArray())
    }

}
