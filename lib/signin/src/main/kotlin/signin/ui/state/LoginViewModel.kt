package signin.ui.state

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.asLog
import logcat.logcat
import signin.domain.CredentialUseCase
import signin.domain.LoginUseCase
import signin.domain.model.Credential
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val credentialUseCase: CredentialUseCase
) : ViewModel() {
    private val _serversScreen = MutableSharedFlow<Unit>()
    val serversScreen = _serversScreen.asSharedFlow()
    private val _uiState = MutableStateFlow(UiState("", "", false, null))
    val uiState = _uiState.asStateFlow()
    private var credentialSaved: Credential? = null
    private var credentialShown = false

    fun signIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val value = _uiState.value
                loginUseCase(value.username, value.password)
                _serversScreen.emit(Unit)
            } catch (e: Exception) {
                logcat(LogPriority.WARN) { e.asLog() }
                _uiState.update { it.copy(error = e.localizedMessage ?: e.javaClass.name) }
            }
            _uiState.update { it.copy(loading = false) }
        }
    }

    fun onUsernameChange(username: String) = _uiState.update { it.copy(username = username) }
    fun onPasswordChange(password: String) = _uiState.update { it.copy(password = password) }

    suspend fun getCredential(activity: Activity) {
        if (credentialShown) return
        credentialShown = true
        credentialUseCase.getCredential(activity)
            ?.let { credential ->
                credentialSaved = credential
                _uiState
                    .update { it.copy(username = credential.id, password = credential.password) }
                signIn()
            }
    }

    suspend fun createCredential(activity: Activity) {
        val value = _uiState.value
        val credentialNew = Credential(value.username, value.password)
        if (credentialNew != credentialSaved)
            credentialUseCase.createCredential(activity, credentialNew)
    }

    data class UiState(
        val username: String, val password: String, val loading: Boolean, val error: String?
    )
}