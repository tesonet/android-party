package feature.main.data.local

import dagger.hilt.android.scopes.ViewModelScoped
import feature.main.data.local.db.dao.ServersDao
import feature.main.data.local.db.model.ServerEntity
import javax.inject.Inject

@ViewModelScoped
class ServersLocalDataSource @Inject constructor(private val serversDao: ServersDao) {
    fun servers() = serversDao.all()
    suspend fun replace(servers: List<ServerEntity>) = serversDao.replaceAll(servers)
    suspend fun delete() = serversDao.deleteAll()
}