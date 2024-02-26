package feature.main.domain

import core.data.repository.TokenRepository
import feature.main.data.repository.ServersRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val serversRepository: ServersRepository
) {
    suspend operator fun invoke() {
        tokenRepository.saveToken(null)
        serversRepository.delete()
    }
}