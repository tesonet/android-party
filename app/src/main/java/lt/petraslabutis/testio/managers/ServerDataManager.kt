package lt.petraslabutis.testio.managers

import lt.petraslabutis.testio.api.AppService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerDataManager @Inject constructor(private val appService: AppService) {

    fun getServerData() = appService.getServers()

}