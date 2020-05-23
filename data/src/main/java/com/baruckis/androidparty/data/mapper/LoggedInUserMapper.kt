package com.baruckis.androidparty.data.mapper

import com.baruckis.androidparty.data.model.LoggedInUserData
import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import javax.inject.Inject

class LoggedInUserMapper @Inject constructor() :
    DataMapper<LoggedInUserData, LoggedInUserEntity> {

    override fun mapFrom(dataModel: LoggedInUserData): LoggedInUserEntity {
        return LoggedInUserEntity(dataModel.token, dataModel.username)
    }

    override fun mapTo(domainEntity: LoggedInUserEntity): LoggedInUserData {
        return LoggedInUserData(domainEntity.token, domainEntity.username)
    }

}