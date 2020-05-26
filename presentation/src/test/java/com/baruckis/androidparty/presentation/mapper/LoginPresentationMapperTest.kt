package com.baruckis.androidparty.presentation.mapper

import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.presentation.TestDataFactory
import com.baruckis.androidparty.presentation.model.LoginPresentation
import org.junit.Test
import kotlin.test.assertEquals

class LoginPresentationMapperTest {

    private val mapper = LoginPresentationMapper()

    @Test
    fun mapToPresentation() {

        val domainModel = TestDataFactory.createLoggedInUserEntity()
        val presentationModel = mapper.mapTo(domainModel)

        assertMapsDataCorrectly(domainModel, presentationModel)
    }

    private fun assertMapsDataCorrectly(
        domainModel: LoggedInUserEntity,
        presentationModel: LoginPresentation
    ) {
        assertEquals(domainModel.username, presentationModel.username)
    }

}