package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.executor.ExecutionThreadScheduler
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.MainRepository
import com.baruckis.androidparty.domain.usecases.base.SingleUseCase
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
                return Params(
                    username,
                    password
                )
            }
        }
    }

}