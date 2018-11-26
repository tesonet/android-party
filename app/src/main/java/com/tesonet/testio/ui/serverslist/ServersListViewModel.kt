package com.tesonet.testio.ui.serverslist

import androidx.lifecycle.ViewModel
import com.tesonet.testio.data.repository.CredentialsRepository
import com.tesonet.testio.data.repository.ServerRepository
import com.tesonet.testio.data.repository.TokenRepository
import javax.inject.Inject


class ServersListViewModel @Inject constructor(
    private val serverRepository: ServerRepository,
    private val credentialsRepository: CredentialsRepository,
    private val tokenRepository: TokenRepository
): ViewModel() {

    fun getServers() = serverRepository.getServersFromLocalDb()

    fun logout() {
        credentialsRepository.deleteCredentials()
        tokenRepository.deleteTokenFromLocalDb()
        serverRepository.deleteAllFromLocalDb()
    }
}