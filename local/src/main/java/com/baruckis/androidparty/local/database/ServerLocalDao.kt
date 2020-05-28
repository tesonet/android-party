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

package com.baruckis.androidparty.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baruckis.androidparty.local.SERVERS_TABLE
import com.baruckis.androidparty.local.model.ServerLocal
import io.reactivex.Flowable

@Dao
interface ServerLocalDao {

    @Query("SELECT * FROM $SERVERS_TABLE")
    fun getServers(): Flowable<List<ServerLocal>>

    @Query("DELETE FROM $SERVERS_TABLE")
    fun deleteServers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveServers(serversList: List<ServerLocal>)

}