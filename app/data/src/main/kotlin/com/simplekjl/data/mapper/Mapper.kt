package com.simplekjl.data.mapper

import com.simplekjl.data.model.ServerDetailsRaw
import com.simplekjl.domain.model.ServerDetails

internal fun ArrayList<ServerDetailsRaw>?.toModel(): List<ServerDetails> {
    val modelList = mutableListOf<ServerDetails>()
    return this?.let {
        it.forEachIndexed { index, item ->
            modelList.add(ServerDetails(index, item.name, item.distance))
        }
        modelList
    } ?: emptyList()
}
