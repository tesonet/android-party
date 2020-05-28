package com.baruckis.androidparty.domain.usecases

import com.baruckis.androidparty.domain.entity.ServerEntity
import com.baruckis.androidparty.domain.qualifiers.Background
import com.baruckis.androidparty.domain.qualifiers.Foreground
import com.baruckis.androidparty.domain.repository.MainRepository
import com.baruckis.androidparty.domain.usecases.base.SingleUseCase
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class FetchServersUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : SingleUseCase<List<ServerEntity>, FetchServersUseCase.DataSource>(
    backgroundScheduler,
    foregroundScheduler
) {

    override fun buildUseCaseSingle(params: DataSource?): Single<List<ServerEntity>> {
        if (params == null) throw IllegalArgumentException("Params can't be null!")
        return when (params) {
            DataSource.LOCAL -> {
                mainRepository.fetchServersFromLocalCache()
            }
            DataSource.REMOTE -> {
                mainRepository.fetchServersFromRemoteApiSaveToDb()
            }
        }
    }

    enum class DataSource {
        LOCAL,
        REMOTE
    }

}

