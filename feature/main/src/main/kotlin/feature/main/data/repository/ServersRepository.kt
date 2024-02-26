package feature.main.data.repository

import androidx.annotation.VisibleForTesting
import core.data.network.api.model.NetworkServer
import dagger.hilt.android.scopes.ViewModelScoped
import feature.main.data.local.ServersLocalDataSource
import feature.main.data.local.db.model.ServerEntity
import feature.main.data.model.Server
import feature.main.data.network.ServersNetworkDataSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ServersRepository @Inject constructor(
    private val serversLocalDataSource: ServersLocalDataSource,
    private val serversNetworkDataSource: ServersNetworkDataSource
) {
    suspend fun newServers(parameter: String) =
        withContext(Dispatchers.IO) {
            serversNetworkDataSource.servers(parameter)
                .map { it.asEntity() }
                .let { serversLocalDataSource.replace(it) }
        }

    fun servers() = serversLocalDataSource.servers().mapToServers().flowOn(Dispatchers.IO)
    suspend fun delete() = withContext(Dispatchers.IO) { serversLocalDataSource.delete() }
    private fun Flow<List<ServerEntity>>.mapToServers() =
        map { entities -> entities.map { it.asExternalModel() }.toImmutableList() }
}

@VisibleForTesting
fun NetworkServer.asEntity() = ServerEntity(name, distance)

@VisibleForTesting
fun ServerEntity.asExternalModel() = Server(name, distance)