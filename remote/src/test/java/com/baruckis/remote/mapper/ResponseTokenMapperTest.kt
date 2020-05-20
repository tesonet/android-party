package com.baruckis.remote.mapper

import com.baruckis.data.model.TokenData
import com.baruckis.remote.TestDataFactory
import com.baruckis.remote.model.ResponseToken
import org.junit.Test
import kotlin.test.assertEquals

class ResponseTokenMapperTest {

    private val mapper = ResponseTokenMapper()

    @Test
    fun mapFrom() {

        val remoteModel = TestDataFactory.createResponseToken()
        val dataModel = mapper.mapFrom(remoteModel)

        assertMapsDataCorrectly(remoteModel, dataModel)
    }

    private fun assertMapsDataCorrectly(remoteModel: ResponseToken, dataModel: TokenData) {
        assertEquals(remoteModel.token, dataModel.token)
    }

}