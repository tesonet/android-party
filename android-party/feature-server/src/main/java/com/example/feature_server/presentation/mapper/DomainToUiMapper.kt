package com.example.feature_server.presentation.mapper

import com.example.core.mapper.Mapper
import com.example.domain_server.domain.model.Server
import com.example.feature_server.presentation.model.ServerUiModel

class DomainToUiMapper : Mapper<Server, ServerUiModel> {
    companion object {
        private const val KILOMETER = "km"
    }

    override fun map(from: Server): ServerUiModel {
        return ServerUiModel(
            serverName = from.name,
            distance = "${from.distance}  $KILOMETER"
        )
    }
}