package lt.havefun.tesonetfunparty.data

import io.reactivex.Single
import lt.havefun.tesonetfunparty.network.Api

data class MainRepository(private val api: Api) {
    fun getServers(): Single<List<Server>?> {
        return api.getServersList()
    }
}