package com.k4dima.androidparty.features.main.domain

import com.k4dima.androidparty.features.app.data.DataRepository
import com.k4dima.androidparty.features.app.data.PreferenceRepository
import com.k4dima.androidparty.features.main.data.model.Server
import com.k4dima.androidparty.features.main.ui.di.MainScope
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@MainScope
class MainUseCase
@Inject
constructor(private val serversRepository: DataRepository<String, List<Server>>,
            private val preferenceRepository: PreferenceRepository) : ServersUseCase {
    override fun data(params: Unit) =
            if (preferenceRepository.token.isEmpty())
                Single.error(Throwable("empty token"))
            else
                serversRepository.data(preferenceRepository.token)
                        .subscribeOn(Schedulers.io())
                        .doOnError {
                            it.printStackTrace()
                        }!!

    override fun logout() {
        preferenceRepository.token = ""
    }
}