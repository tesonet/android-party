package lt.petraslabutis.testio.api

import io.reactivex.Observable
import lt.petraslabutis.testio.api.model.ServerResponse
import retrofit2.http.GET

interface AppService {

    @GET("servers")
    fun getServers(): Observable<List<ServerResponse>>

}