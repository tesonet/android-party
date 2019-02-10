package lt.petraslabutis.tesonetapplication.managers

import com.securepreferences.SecurePreferences
import lt.petraslabutis.tesonetapplication.api.AuthenticationService
import lt.petraslabutis.tesonetapplication.api.model.LoginRequest
import javax.inject.Inject

class AuthenticationManager @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val preferences: SecurePreferences
) {

    companion object {
        const val AUTH_TOKEN = "authentication_token_storage"
    }

    fun login(username: String, password: String) =
        authenticationService
            .login(LoginRequest(username, password))
            .doOnNext { preferences.edit().putString(AUTH_TOKEN, it.token).apply() }

    fun isAuthenticated() = !preferences.getString(AUTH_TOKEN, "").isNullOrEmpty()

}
