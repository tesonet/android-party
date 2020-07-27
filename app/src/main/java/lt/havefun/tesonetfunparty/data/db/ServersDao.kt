package lt.havefun.tesonetfunparty.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import lt.havefun.tesonetfunparty.data.Server

@Dao
interface ServersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(servers: List<Server>)

    @Query("SELECT * FROM ${Server.TABLE_NAME}")
    fun getAll(): List<Server>

    @Query("DELETE FROM ${Server.TABLE_NAME}")
    fun deleteAll()
}
