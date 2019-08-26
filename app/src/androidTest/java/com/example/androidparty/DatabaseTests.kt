package com.example.androidparty

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidparty.database.ServerEntity
import com.example.androidparty.database.ServersDB
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTests {
    private var serversDB: ServersDB? = null

    @Before
    fun setup() {
        ServersDB.TEST_MODE = true
        serversDB = ServersDB.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @Test
    fun should_Insert_Server_Item() {
        val server = ServerEntity("serverName", 123)
        serversDB?.serverDao()!!.addServer(server)
        val serverTest = serversDB?.serverDao()!!.getServers()
        Assert.assertEquals(server.serverName, serverTest[0].serverName)
    }
}