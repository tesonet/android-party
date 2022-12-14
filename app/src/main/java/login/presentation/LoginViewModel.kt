package login.presentation

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.domain.DataRepository
import app.domain.PreferenceRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import login.domain.model.Token
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import javax.inject.Inject

@ActivityRetainedScoped
class LoginViewModel
@Inject
constructor(
    private val tokenRepository: DataRepository<Map<String, RequestBody>, Token>,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    val main = MutableLiveData<Unit>()
    val error = ObservableField<String>()
    val username = ObservableField("")
    val password = ObservableField("")
    val refreshing = ObservableBoolean()

    fun login(@Suppress("UNUSED_PARAMETER") button: View) {
        viewModelScope.launch {
            try {
                refreshing.set(true)
                val requestBodyMap = mapOf(
                    "username" to username.get()!!.toRequestBody("text/plain".toMediaTypeOrNull()),
                    "password" to password.get()!!.toRequestBody("text/plain".toMediaTypeOrNull())
                )
                withContext(Dispatchers.IO) {
                    preferenceRepository.token = tokenRepository.data(requestBodyMap).token
                }
                main.value = Unit
            } catch (e: Exception) {
                Timber.e(e)
                error.set(e.localizedMessage ?: e.javaClass.name)
            } finally {
                refreshing.set(false)
            }
        }
    }
}