package com.sena.practica_de_consumo_api.domain.usercase



import com.sena.practica_de_consumo_api.data.repository.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository = AuthRepository()
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.login(email, password)
}
