package lt.petraslabutis.tesonetapplication.api

import io.reactivex.Observable
import lt.petraslabutis.tesonetapplication.api.model.ServerResponse
import retrofit2.http.GET

interface AppService {

    @GET("servers")
    fun getServers(): Observable<List<ServerResponse>>

}