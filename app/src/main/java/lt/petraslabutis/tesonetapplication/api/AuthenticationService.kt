package lt.petraslabutis.tesonetapplication.api

import io.reactivex.Observable
import lt.petraslabutis.tesonetapplication.api.model.LoginRequest
import lt.petraslabutis.tesonetapplication.api.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST("tokens")
    fun login(@Body request: LoginRequest): Observable<LoginResponse>

}
