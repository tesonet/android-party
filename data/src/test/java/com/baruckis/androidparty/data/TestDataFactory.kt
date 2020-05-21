package com.baruckis.androidparty.data

import com.baruckis.androidparty.data.model.TokenData
import com.baruckis.androidparty.domain.entity.TokenEntity

object TestDataFactory {

    private const val TOKEN = "f9731b590611a5a9377fbd02f247fcdf"

    fun createTokenEntity(): TokenEntity {
        return TokenEntity(TOKEN)
    }

    fun createTokenData(): TokenData {
        return TokenData(TOKEN)
    }

}