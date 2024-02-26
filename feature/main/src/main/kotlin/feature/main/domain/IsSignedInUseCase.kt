package feature.main.domain

import core.data.repository.TokenRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsSignedInUseCase @Inject constructor(private val tokenRepository: TokenRepository) {
    operator fun invoke() = tokenRepository.localTokens().map { !it.isNullOrEmpty() }
}