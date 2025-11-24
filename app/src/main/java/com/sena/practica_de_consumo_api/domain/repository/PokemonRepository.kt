package com.sena.practica_de_consumo_api.domain.repository

import com.sena.practica_de_consumo_api.domain.model.Pokemon

interface PokemonRepository {

    /**
     * Obtiene la lista de pokemones desde la fuente de datos
     *
     * Más adelante la implementacion usará Retrofit para llamar a la API
     */
    suspend fun getPokemon(): List<Pokemon>
}