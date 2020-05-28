package com.baruckis.androidparty.remote.mapper

import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.remote.TestDataFactory
import com.baruckis.androidparty.remote.model.ResponseToken
import org.junit.Test
import kotlin.test.assertEquals

class ResponseTokenMapperTest {

    private val mapper =
        ResponseTokenMapper()

    @Test
    fun mapFrom() {

        val remoteModel = TestDataFactory.createResponseToken()
        val dataModel = mapper.mapFromRemote(remoteModel)

        assertMapsDataCorrectly(remoteModel, dataModel)
    }

    private fun assertMapsDataCorrectly(remoteModel: ResponseToken, dataModel: TokenData) {
        assertEquals(remoteModel.token, dataModel.token)
    }

}