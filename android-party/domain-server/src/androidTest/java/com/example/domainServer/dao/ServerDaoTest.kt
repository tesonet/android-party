package com.example.domainServer.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.example.domainServer.data.db.ServerDatabase
import com.example.domainServer.data.db.dao.ServerDao
import com.example.domainServer.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ServerDaoTest {
    private lateinit var dao: ServerDao
    private lateinit var db: ServerDatabase
    private val testData = ServerEntity(name = "Test Server 1", distance = 123)

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ServerDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = db.serverDao()
    }

    private fun getServerList() = listOf(
        testData,
        testData.copy(name = "Test Server 2"),
        testData.copy(name = "Test Server 3")
    )

    @Test
    @Throws(Exception::class)
    fun testInsertInServerDao() {
        val provided = getServerList()
        val returnList = dao.insertServers(provided)
        assert(returnList.size == 3)
    }

    @Test
    @Throws(Exception::class)
    fun testQueryInServerDao() = runTest {
        val provided = getServerList()
        val returnList = dao.insertServers(provided)
        assert(returnList.size == 3)
        dao.fetchAllServers().take(1).collect {
            assert(provided == it)
        }
    }

    @After
    @Throws(IOException::class)
    fun end() {
        db.close()
    }
}