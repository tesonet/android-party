package signin.domain

import android.app.Activity
import android.content.Context
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.GetCredentialException
import dagger.hilt.android.qualifiers.ApplicationContext
import logcat.LogPriority
import logcat.asLog
import logcat.logcat
import signin.domain.model.Credential
import javax.inject.Inject

class CredentialUseCase @Inject constructor(@ApplicationContext context: Context) {
    private val credentialManager = CredentialManager.create(context)

    suspend fun getCredential(activity: Activity) = try {
        val request = GetCredentialRequest(listOf(GetPasswordOption()))
        val response = credentialManager.getCredential(activity, request)
        val passwordCredential = response.credential as PasswordCredential
        passwordCredential.toCredential()
    } catch (e: GetCredentialException) {
        logcat(LogPriority.WARN) { e.asLog() }
        null
    }

    suspend fun createCredential(activity: Activity, credential: Credential) {
        try {
            val request = CreatePasswordRequest(credential.id, credential.password)
            credentialManager.createCredential(activity, request)
        } catch (e: CreateCredentialException) {
            logcat(LogPriority.WARN) { e.asLog() }
        }
    }

    private fun PasswordCredential.toCredential() = Credential(id, password)
}