package feature.main.data.network

import core.data.network.api.TestioService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ServersNetworkDataSource @Inject constructor(private val testioService: TestioService) {
    suspend fun servers(token: String) = testioService.servers(token)
}