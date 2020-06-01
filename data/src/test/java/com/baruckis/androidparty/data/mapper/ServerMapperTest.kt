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

package com.baruckis.androidparty.data.mapper

import com.baruckis.androidparty.data.DataTestDataFactory
import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.domain.entity.ServerEntity
import org.junit.Test
import kotlin.test.assertEquals

class ServerMapperTest {

    private val mapper = ServerMapper()

    @Test
    fun mapFromData() {

        val dataModel = DataTestDataFactory.createServerData()
        val domainEntity = mapper.mapFromData(dataModel)

        assertMapsDataCorrectly(dataModel, domainEntity)
    }

    @Test
    fun mapToData() {

        val domainEntity = DataTestDataFactory.createServerEntity()
        val dataModel = mapper.mapToData(domainEntity)

        assertMapsDataCorrectly(dataModel, domainEntity)
    }

    private fun assertMapsDataCorrectly(
        dataModel: ServerData,
        domainEntity: ServerEntity
    ) {
        assertEquals(dataModel.name, domainEntity.name)
        assertEquals(dataModel.distance, domainEntity.distance)
    }

}