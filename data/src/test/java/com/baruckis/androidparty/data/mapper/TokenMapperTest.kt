package com.baruckis.androidparty.data.mapper

import com.baruckis.androidparty.data.mapper.TokenMapper
import com.baruckis.androidparty.data.TestDataFactory
import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.TokenEntity
import org.junit.Test
import kotlin.test.assertEquals

class TokenMapperTest {

    private val mapper = TokenMapper()

    @Test
    fun mapFrom() {

        val dataModel = TestDataFactory.createTokenData()
        val domainEntity = mapper.mapFrom(dataModel)

        assertMapsDataCorrectly(dataModel, domainEntity)
    }

    @Test
    fun mapTo() {

        val domainEntity = TestDataFactory.createTokenEntity()
        val dataModel = mapper.mapTo(domainEntity)

        assertMapsDataCorrectly(dataModel, domainEntity)
    }

    private fun assertMapsDataCorrectly(dataModel: TokenData, domainEntity: TokenEntity) {
        assertEquals(dataModel.token, domainEntity.token)
    }

}