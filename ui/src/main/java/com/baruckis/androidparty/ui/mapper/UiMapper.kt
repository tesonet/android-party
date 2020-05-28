package com.baruckis.androidparty.ui.mapper

interface UiMapper<PresentationModel, UiModel> {

    fun mapToUi(presentationModel: PresentationModel): UiModel

}