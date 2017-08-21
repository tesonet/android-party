package lt.marius.testio.api

import io.reactivex.Observable
import lt.marius.testio.model.LoginRequestBody
import lt.marius.testio.model.LoginResponse
import lt.marius.testio.model.Server
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by marius on 17.8.21.
 */
interface AppService {
	@POST("tokens")
	fun login(@Body body: LoginRequestBody): Observable<LoginResponse>

	@GET("servers")
	fun getServers(): Observable<List<Server>>

}