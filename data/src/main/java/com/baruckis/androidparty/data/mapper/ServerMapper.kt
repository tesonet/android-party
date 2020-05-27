package com.baruckis.androidparty.data.mapper

import com.baruckis.androidparty.data.model.ServerData
import com.baruckis.androidparty.domain.entity.ServerEntity
import javax.inject.Inject

class ServerMapper @Inject constructor() :
    DataMapper<ServerData, ServerEntity> {

    override fun mapFrom(dataModel: ServerData): ServerEntity {
        return ServerEntity(dataModel.name, dataModel.distance)
    }

    override fun mapTo(domainEntity: ServerEntity): ServerData {
        return ServerData(domainEntity.name, domainEntity.distance)
    }

}