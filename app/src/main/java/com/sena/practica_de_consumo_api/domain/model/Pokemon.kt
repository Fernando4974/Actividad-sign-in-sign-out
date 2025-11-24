package com.sena.practica_de_consumo_api.domain.model

/**
 * Modelo de dominio que representa a un usuario/pokemon.
 *
 * Esta es la clase que usara la UI y la logica de negocio.
 * Mas adelante convertiremos el JSON de la API a este modelo.
 */

data class Pokemon(
    val name: String,
    val url: String
)
