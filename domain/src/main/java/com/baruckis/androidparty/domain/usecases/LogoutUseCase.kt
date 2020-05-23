package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.repository.MainRepository
import com.baruckis.androidparty.domain.usecases.base.SynchronousUseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val mainRepository: MainRepository
) : SynchronousUseCase<Unit, Any> {

    override fun execute(params: Any?) {
        return mainRepository.logout()
    }

}