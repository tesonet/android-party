package com.baruckis.androidparty.presentation.mapper

import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.presentation.model.LoginPresentation
import javax.inject.Inject

class LoginPresentationMapper @Inject constructor() :
    PresentationMapper<LoginPresentation, LoggedInUserEntity> {

    override fun mapTo(domainEntity: LoggedInUserEntity): LoginPresentation {
        return LoginPresentation(
            domainEntity.username
        )
    }

}