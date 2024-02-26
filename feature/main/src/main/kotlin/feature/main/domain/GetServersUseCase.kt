package feature.main.domain

import feature.main.data.repository.ServersRepository
import javax.inject.Inject

class GetServersUseCase @Inject constructor(private val serversRepository: ServersRepository) {
    operator fun invoke() = serversRepository.servers()
}