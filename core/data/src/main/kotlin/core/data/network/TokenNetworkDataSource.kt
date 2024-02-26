package core.data.network

import core.data.network.api.TestioService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import javax.inject.Inject

@ActivityRetainedScoped
class TokenNetworkDataSource @Inject constructor(private val testioService: TestioService) {
    suspend fun token(authorization: Map<String, RequestBody>) =
        withContext(Dispatchers.IO) { testioService.token(authorization) }
}