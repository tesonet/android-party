package com.baruckis.androidparty.ui.mapper

import com.baruckis.androidparty.presentation.model.LoginPresentation
import com.baruckis.androidparty.ui.model.LoginUi
import javax.inject.Inject

class LoginUiMapper @Inject constructor() : UiMapper<LoginPresentation, LoginUi> {

    override fun mapToUi(presentationModel: LoginPresentation): LoginUi {
        return LoginUi(presentationModel.username)
    }

}