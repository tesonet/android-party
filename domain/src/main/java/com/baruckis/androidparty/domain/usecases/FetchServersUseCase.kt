/*
 * Copyright 2020 Andrius Baruckis www.baruckis.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

