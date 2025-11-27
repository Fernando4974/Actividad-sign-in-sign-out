package com.sena.practica_de_consumo_api.data.repository



import com.sena.practica_de_consumo_api.data.remote.AuthService

class AuthRepository {
    suspend fun login(email: String, password: String) =
        AuthService.login(email, password)
}