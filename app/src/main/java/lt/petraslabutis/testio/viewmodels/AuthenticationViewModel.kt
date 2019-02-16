package lt.petraslabutis.testio.viewmodels

import androidx.lifecycle.ViewModel
import com.securepreferences.SecurePreferences
import io.reactivex.Observable
import lt.petraslabutis.testio.api.AuthenticationService
import lt.petraslabutis.testio.api.model.LoginRequest
import lt.petraslabutis.testio.api.model.LoginResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val preferences: SecurePreferences
) : ViewModel() {

    companion object {
        const val AUTH_TOKEN = "authentication_token_storage"
    }

    fun login(username: String, password: String): Observable<LoginResponse> =
        authenticationService
            .login(LoginRequest(username, password))
            .doOnNext {
                preferences.edit().putString(AUTH_TOKEN, it.token).apply()
            }

    fun isLoggedIn(): Boolean = preferences.getString(AUTH_TOKEN, "")?.isNotEmpty() == true

    fun logout() {
        preferences.edit().clear().apply()
    }


}