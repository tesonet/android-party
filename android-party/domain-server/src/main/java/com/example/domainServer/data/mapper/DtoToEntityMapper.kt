package com.example.domainServer.data.mapper

import com.example.core.mapper.Mapper
import com.example.domainServer.data.db.entity.ServerEntity
import com.example.domainServer.data.dto.ServerDto

class DtoToEntityMapper : Mapper<ServerDto, ServerEntity> {
    override fun map(from: ServerDto): ServerEntity {
        return ServerEntity(
            name = from.name,
            distance = from.distance
        )
    }
}
