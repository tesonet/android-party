package feature.main.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import feature.main.data.local.db.model.ServerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServersDao {
    @Insert
    suspend fun insertAll(servers: List<ServerEntity>)

    @Query("SELECT * FROM servers")
    fun all(): Flow<List<ServerEntity>>

    @Query("DELETE FROM servers")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(servers: List<ServerEntity>) {
        deleteAll()
        insertAll(servers)
    }
}