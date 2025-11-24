package com.sena.practica_de_consumo_api.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sena.practica_de_consumo_api.domain.usercase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUiState())

    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    init {
        loadPokemon()
    }


    fun loadPokemon() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val pokemon = getPokemonUseCase()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    pokemon = pokemon,
                    errorMessage = null
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Ocurrió un error al cargar los pokemon"
                )
            }
        }
    }
}