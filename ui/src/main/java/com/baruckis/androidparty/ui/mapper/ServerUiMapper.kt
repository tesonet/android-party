package com.baruckis.androidparty.ui.mapper

import com.baruckis.androidparty.presentation.model.ServerPresentation
import com.baruckis.androidparty.ui.model.ServerUi
import javax.inject.Inject

class ServerUiMapper @Inject constructor() : UiMapper<ServerPresentation, ServerUi> {

    override fun mapToUi(presentationModel: ServerPresentation): ServerUi {
        return ServerUi(presentationModel.name, presentationModel.distance.toString())
    }

}