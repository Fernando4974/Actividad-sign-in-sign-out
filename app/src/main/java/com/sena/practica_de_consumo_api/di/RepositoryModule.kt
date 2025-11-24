package com.sena.practica_de_consumo_api.di

import com.sena.practica_de_consumo_api.data.repository.PokemonRepositoryImpl
import com.sena.practica_de_consumo_api.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository {
        return pokemonRepositoryImpl
    }
}

