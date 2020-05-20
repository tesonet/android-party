package com.baruckis.data.mapper

import com.baruckis.data.model.TokenData
import com.baruckis.domain.entity.TokenEntity
import javax.inject.Inject

class TokenMapper @Inject constructor() : DataMapper<TokenData, TokenEntity> {

    override fun mapFrom(dataModel: TokenData): TokenEntity {
        return TokenEntity(dataModel.token)
    }

    override fun mapTo(domainEntity: TokenEntity): TokenData {
        return TokenData(domainEntity.token)
    }

}