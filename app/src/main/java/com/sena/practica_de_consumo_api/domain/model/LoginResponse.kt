package com.sena.practica_de_consumo_api.domain.model

class LoginResponse {
    val message: String? = null
    val token: String? = null
    val user: User? = null

    inner class User {
        val id: Int = 0
        val email: String? = null
    }
}
