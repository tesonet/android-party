package com.tesonet.testio.ui.login

import androidx.lifecycle.ViewModel
import com.tesonet.testio.data.local.entity.Credentials
import com.tesonet.testio.data.repository.CredentialsRepository
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val credentialsRepository: CredentialsRepository
): ViewModel() {

    fun getCredentials() = credentialsRepository.getCredentials()

    fun setCredentials(credentials: Credentials) {
        credentialsRepository.saveCredentials(credentials)
    }
}