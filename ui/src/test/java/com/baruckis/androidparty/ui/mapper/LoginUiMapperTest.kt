package com.baruckis.androidparty.ui.mapper

import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.ui.TestDataFactory
import com.baruckis.androidparty.ui.model.LoginUi
import org.junit.Test
import kotlin.test.assertEquals

class LoginUiMapperTest {

    private val mapper = LoginUiMapper()

    @Test
    fun mapToUi() {

        val presentationModel = TestDataFactory.createLoginPresentation()
        val uiModel = mapper.mapTo(presentationModel)

        assertMapsDataCorrectly(presentationModel, uiModel)
    }

    private fun assertMapsDataCorrectly(presentationModel: LoginPresentation, uiModel: LoginUi) {
        assertEquals(presentationModel.username, uiModel.username)
    }

}