package com.sena.practica_de_consumo_api.data.remote

import com.sena.practica_de_consumo_api.data.remote.dto.LoginRequest
import com.sena.practica_de_consumo_api.data.remote.dto.LoginResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

object AuthService {

    suspend fun login(email: String, password: String): LoginResponse {
        return ApiClient.client.post("${ApiClient.BASE_URL}/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email, password))
        }.body()
    }
}
