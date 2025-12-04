package com.example.authapp.data.models

data class AuthResponse(
    val message: String,
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val email: String,
    val username: String
)
