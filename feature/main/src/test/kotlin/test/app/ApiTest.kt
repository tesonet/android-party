package test.app

import core.data.network.TokenNetworkDataSource
import core.data.network.api.TestioService
import core.data.network.api.model.NetworkServer
import feature.main.data.network.ServersNetworkDataSource
import feature.main.data.repository.asEntity
import feature.main.data.repository.asExternalModel
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.core.assertServers

class ApiTest {
    @Test
    fun walkthrough() = runTest {
        val service = Retrofit.Builder()
            .baseUrl("https://playground.tesonet.lt/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(TestioService::class.java)
        val dataSource = TokenNetworkDataSource(service)
        val authorization = mapOf("username" to "tesonet", "password" to "partyanimal")
            .mapValues { it.value.toRequestBody("text/plain".toMediaTypeOrNull()) }
        val token = dataSource.token(authorization).token
        assertEquals("f9731b590611a5a9377fbd02f247fcdf", token)
        val serversNetworkDataSource = ServersNetworkDataSource(service)
        val servers = serversNetworkDataSource.servers(token)
        assertServers(servers)
    }

    private fun assertServers(servers: List<NetworkServer>) =
        servers
            .map { it.asEntity().asExternalModel() }
            .let { assertServers(it) }
}