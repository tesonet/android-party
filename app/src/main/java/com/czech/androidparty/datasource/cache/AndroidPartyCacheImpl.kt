package com.czech.androidparty.datasource.cache

import com.czech.androidparty.Database
import com.czech.androidparty.models.DataList
import com.czech.androidparty.utils.SqlConverter.toDataList

class AndroidPartyCacheImpl(
    database: Database
): AndroidPartyCache {

    private val queries: AndroidPartyDBQueries = database.androidPartyDBQueries

    override fun insertData(data: DataList) {
        queries.insertData(
            name = data.name,
            distance = data.distance.toLong()
        )
    }

    override fun insertData(dataList: List<DataList>) {
        for (data in dataList) {
            insertData(data)
        }
    }

    override fun getData(): List<DataList> {
        return queries.getData().executeAsList().toDataList()
    }

    override fun deleteData() {
        return queries.transaction {
            queries.deleteData()
        }
    }
}