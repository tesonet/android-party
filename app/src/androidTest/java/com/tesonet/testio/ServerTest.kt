package com.tesonet.testio

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tesonet.testio.services.data.server.Server
import com.tesonet.testio.services.database.ServersDao
import com.tesonet.testio.services.database.ServersDatabase
import org.junit.*
import org.junit.runner.*

@RunWith(AndroidJUnit4::class)
class ServerTest {

    lateinit var database: ServersDatabase
    lateinit var dao: ServersDao

    @Before
    fun setup() {
        database =
            Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ServersDatabase::class.java)
                .allowMainThreadQueries().build()

        dao = database.serversDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertServerItem() {
        dao.addAllServers(servers)
        dao.readAllServers()
            .test()
            .assertValues(servers)
            .dispose()
    }

    @Test
    fun deleteAllServers() {
        dao.addAllServers(servers)
        dao.deleteAllServers()
        dao.readAllServers()
            .test()
            .assertValue { servers ->
                servers.isEmpty()
            }.dispose()
    }

    companion object {
        private val servers = listOf(Server("Lithuania", "999"), Server("UK", "888"))
    }
}