package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.entity.TokenEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.MainRepository
import com.baruckis.androidparty.domain.usecases.base.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : SingleUseCase<TokenEntity, LoginUseCase.Params>(backgroundScheduler, foregroundScheduler) {

    override fun buildUseCaseSingle(params: Params?): Single<TokenEntity> {
        requireNotNull(params) { "Params can't be null!" }
        return mainRepository.login(params.username, params.password)
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