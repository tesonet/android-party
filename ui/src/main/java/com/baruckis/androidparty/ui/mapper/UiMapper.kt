package com.baruckis.androidparty.ui.mapper

interface UiMapper<PresentationModel, UiModel> {

    fun mapTo(presentation: PresentationModel): UiModel

}