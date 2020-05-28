package com.baruckis.androidparty.remote.mapper

interface ApiResponseMapper<RemoteModel, DataModel> {

    fun mapFromRemote(remoteModel: RemoteModel): DataModel
}