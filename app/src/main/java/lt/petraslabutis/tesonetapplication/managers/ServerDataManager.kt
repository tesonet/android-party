package lt.petraslabutis.tesonetapplication.managers

import lt.petraslabutis.tesonetapplication.api.AppService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerDataManager @Inject constructor(private val appService: AppService) {

    fun getServerData() = appService.getServers()

}