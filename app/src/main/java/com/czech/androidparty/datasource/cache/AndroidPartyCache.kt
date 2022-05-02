package com.czech.androidparty.datasource.cache

import com.czech.androidparty.models.DataList

interface AndroidPartyCache {

    fun insertData(data: DataList)

    fun insertData(dataList: List<DataList>)

    fun getData(): List<DataList>

    fun deleteData()
}