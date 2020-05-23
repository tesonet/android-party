package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.entity.LoggedInUserEntity
import com.baruckis.androidparty.domain.repository.MainRepository
import com.baruckis.androidparty.domain.usecases.base.SynchronousUseCase
import javax.inject.Inject


class GetLoggedInUser @Inject constructor(
    private val mainRepository: MainRepository
) : SynchronousUseCase<LoggedInUserEntity?, Any> {

    override fun execute(params: Any?): LoggedInUserEntity? {
        return mainRepository.getLoggedInUser()
    }

}