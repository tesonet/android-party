package feature.main.domain

import core.data.repository.TokenRepository
import feature.main.data.repository.ServersRepository
import javax.inject.Inject

class SyncServersUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val serversRepository: ServersRepository
) {
    suspend operator fun invoke() {
        val token = tokenRepository.savedToken() ?: throw NoAccessToken()
        serversRepository.newServers(token)
    }

    class NoAccessToken : Exception()
}