package lt.petraslabutis.testio.viewmodels

import androidx.lifecycle.ViewModel
import lt.petraslabutis.testio.api.AppService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServerListViewModel @Inject constructor(
    private val appService: AppService
) : ViewModel() {

    fun getServerData() = appService.getServers()

}