package com.example.domain_server.data.mapper

import com.example.core.mapper.Mapper
import com.example.domain_server.data.db.entity.ServerEntity
import com.example.domain_server.data.dto.ServerDto

class DtoToEntityMapper : Mapper<ServerDto, ServerEntity> {
    override fun map(from: ServerDto): ServerEntity {
        return ServerEntity(
            name = from.name,
            distance = from.distance
        )
    }
}