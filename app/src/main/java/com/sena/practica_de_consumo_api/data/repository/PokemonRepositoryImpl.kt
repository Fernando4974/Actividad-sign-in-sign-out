package com.sena.practica_de_consumo_api.data.repository

import com.sena.practica_de_consumo_api.data.PokemonApi
import com.sena.practica_de_consumo_api.data.remote.dto.toDomain
import com.sena.practica_de_consumo_api.domain.model.Pokemon
import com.sena.practica_de_consumo_api.domain.repository.PokemonRepository
import jakarta.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemon(): List<Pokemon> {
        val response = pokemonApi.getPokemon()

        return response.results.map { it.toDomain() }
    }
}