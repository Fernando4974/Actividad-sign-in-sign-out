package com.sena.practica_de_consumo_api.domain.usercase

import com.sena.practica_de_consumo_api.domain.model.Pokemon
import com.sena.practica_de_consumo_api.domain.repository.PokemonRepository
import javax.inject.Inject

/**
 * Caso de uso responsable de obtener la lista de usuarios.
 *
 * Ventajas:
 * - Encapsula la logica de negocio ( aunque hoy solo delegue)
 * - Facilita pruebas unitarias.
 * - Si mañana hay reglas extra ( filtros, orden, caché), se ponen aquí
 */

class GetPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {

    /**
     * Ejecuta el caso de uso
     *
     * Usamos operator fun invoke() para poder llamarlo como:
     * getUserUseCase()
     */

    suspend operator fun invoke(): List<Pokemon> {
        return pokemonRepository.getPokemon()
    }
}