package com.czech.androidparty.utils

import com.czech.androidparty.datasource.cache.Data_Entity
import com.czech.androidparty.models.DataList

object SqlConverter {

    private fun Data_Entity.toData(): DataList {
        return DataList(
            name = name,
            distance = distance.toInt()
        )
    }

    fun List<Data_Entity>.toDataList(): List<DataList> {
        return map { it.toData() }
    }
}