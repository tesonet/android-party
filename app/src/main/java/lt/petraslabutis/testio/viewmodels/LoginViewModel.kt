package lt.petraslabutis.testio.viewmodels

import androidx.lifecycle.ViewModel
import com.securepreferences.SecurePreferences
import lt.petraslabutis.testio.api.AuthenticationService
import lt.petraslabutis.testio.api.model.LoginRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginViewModel @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val preferences: SecurePreferences
) : ViewModel() {

    companion object {
        const val AUTH_TOKEN = "authentication_token_storage"
    }

    fun login(username: String, password: String) =
        authenticationService
            .login(LoginRequest(username, password))
            .doOnNext {
                preferences.edit().putString(AUTH_TOKEN, it.token).apply()
            }

    fun logout() {
        preferences.destroyKeys()
    }


}