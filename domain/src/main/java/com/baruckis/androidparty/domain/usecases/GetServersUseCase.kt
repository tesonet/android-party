package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.MainRepository
import com.baruckis.androidparty.domain.usecases.base.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetServersUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : SingleUseCase<List<ServerEntity>, Any>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun buildUseCaseSingle(params: Any?): Single<List<ServerEntity>> {
        return mainRepository.getServers()
    }

}