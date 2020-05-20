package com.baruckis.domain.usecases

import com.baruckis.domain.entity.TokenEntity
import com.baruckis.domain.executor.ExecutionThreadScheduler
import com.baruckis.domain.qualifiers.Background
import com.baruckis.domain.qualifiers.Foreground
import com.baruckis.domain.repository.MainRepository
import com.baruckis.domain.usecases.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SendAuthorization @Inject constructor(
    private val mainRepository: MainRepository,
    @Background backgroundExecutor: ExecutionThreadScheduler,
    @Foreground foregroundExecutor: ExecutionThreadScheduler
) : SingleUseCase<TokenEntity, SendAuthorization.Params>(backgroundExecutor, foregroundExecutor) {

    override fun buildUseCaseSingle(params: Params?): Single<TokenEntity> {
        requireNotNull(params) { "Params can't be null!" }
        return mainRepository.sendAuthorization(params.username, params.password)
    }

    data class Params constructor(val username: String, val password: String) {
        companion object {
            fun authorization(username: String, password: String): Params {
                return Params(username, password)
            }
        }
    }

}