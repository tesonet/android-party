/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baruckis.androidparty.local

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.local.database.AppDatabase
import com.baruckis.androidparty.local.mapper.LocalServerMapper
import com.baruckis.androidparty.local.model.ServerLocal
import com.baruckis.androidparty.local.preferences.PreferenceStorage
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// Robolectric setup
@RunWith(RobolectricTestRunner::class)
// Robolectric is telling me that Java 9 is required.
// Configure Robolectric to emulate a lower SDK in a specific test.
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class LocalDataSourceImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()

    private val sharedPreferenceStorage = Mockito.mock(PreferenceStorage::class.java)
    private val localServerMapper = Mockito.mock(LocalServerMapper::class.java)

    private val localDataSource =
        LocalDataSourceImpl(sharedPreferenceStorage, database, localServerMapper)


    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun clearServersCompletes() {
        val testObserver = localDataSource.clearServers().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveServersCompletes() {
        val serverData = LocalTestDataFactory.createServerData()
        stubLocalServerMapperMapToLocal(
            MockitoHelper.anyObject(),
            LocalTestDataFactory.createServerLocal()
        )

        val testObserver =
            localDataSource.saveServers(listOf(serverData)).test()
        testObserver.assertComplete()
    }

    @Test
    fun getServersReturnsData() {
        val serverData = LocalTestDataFactory.createServerData()
        val serverLocal = LocalTestDataFactory.createServerLocal()
        val serverDataList = listOf(serverData)

        stubLocalServerMapperMapToLocal(serverData, serverLocal)
        localDataSource.saveServers(serverDataList).test()

        stubLocalServerMapperMapFromLocal(serverLocal, serverData)
        val testObserver = localDataSource.getServers().test()
        testObserver.assertValue(serverDataList)
    }

    private fun stubLocalServerMapperMapFromLocal(
        localModel: ServerLocal,
        dataModel: ServerData
    ) {
        Mockito.`when`(localServerMapper.mapFromLocal(localModel))
            .thenReturn(dataModel)
    }

    private fun stubLocalServerMapperMapToLocal(
        dataModel: ServerData,
        localModel: ServerLocal
    ) {
        Mockito.`when`(localServerMapper.mapToLocal(dataModel))
            .thenReturn(localModel)
    }

    // Matches any object, excluding nulls.
    object MockitoHelper {
        fun <T> anyObject(): T {
            Mockito.any<T>()
            return uninitialized()
        }

        @Suppress("UNCHECKED_CAST")
        fun <T> uninitialized(): T = null as T
    }

}