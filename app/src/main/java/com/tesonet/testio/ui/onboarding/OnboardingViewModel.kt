package com.tesonet.testio.ui.onboarding

import androidx.lifecycle.ViewModel
import com.tesonet.testio.data.repository.CredentialsRepository
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val credentialsRepository: CredentialsRepository
): ViewModel() {

    fun getCredentials() = credentialsRepository.getCredentials()
}