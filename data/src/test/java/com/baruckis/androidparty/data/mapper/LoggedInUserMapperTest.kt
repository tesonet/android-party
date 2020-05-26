package com.baruckis.androidparty.data.mapper

import com.baruckis.androidparty.data.TestDataFactory
import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import org.junit.Test
import kotlin.test.assertEquals

class LoggedInUserMapperTest {

    private val mapper = LoggedInUserMapper()

    @Test
    fun mapFrom() {

        val dataModel = TestDataFactory.createLoggedInUserData()
        val domainEntity = mapper.mapFrom(dataModel)

        assertMapsDataCorrectly(dataModel, domainEntity)
    }

    @Test
    fun mapTo() {

        val domainEntity = TestDataFactory.createLoggedInUserEntity()
        val dataModel = mapper.mapTo(domainEntity)

        assertMapsDataCorrectly(dataModel, domainEntity)
    }

    private fun assertMapsDataCorrectly(
        dataModel: LoggedInUserData,
        domainEntity: LoggedInUserEntity
    ) {
        assertEquals(dataModel.token, domainEntity.token)
        assertEquals(dataModel.username, domainEntity.username)
    }

}