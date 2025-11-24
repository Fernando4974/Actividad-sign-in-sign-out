package com.sena.practica_de_consumo_api.presentation.user

import com.sena.practica_de_consumo_api.domain.model.Pokemon

/**
 * Representa el estado completo de la pantalla de usuarios.
 *
 * Ventajas:
 * -Es inmutable
 * -La UI
 */

data class PokemonUiState(
    val isLoading: Boolean = false,
    val pokemon: List<Pokemon> = emptyList(),
    val errorMessage: String? = null
)
