package com.sena.practica_de_consumo_api.data

import com.sena.practica_de_consumo_api.data.remote.dto.PokemonListResponseDto
import retrofit2.http.GET

interface PokemonApi {


    @GET("pokemon")
    suspend fun getPokemon(): PokemonListResponseDto
}