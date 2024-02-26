package signin.domain

interface LoginUseCase {
    suspend operator fun invoke(username: String, password: String)
}