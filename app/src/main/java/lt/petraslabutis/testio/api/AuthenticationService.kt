package lt.petraslabutis.testio.api

import io.reactivex.Observable
import lt.petraslabutis.testio.api.model.LoginRequest
import lt.petraslabutis.testio.api.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("tokens")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

}
