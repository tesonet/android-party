package com.baruckis.androidparty.local.mapper

interface LocalMapper<LocalModel, DataModel> {

    fun mapFromLocal(localModel: LocalModel): DataModel

    fun mapToLocal(dataModel: DataModel): LocalModel

}