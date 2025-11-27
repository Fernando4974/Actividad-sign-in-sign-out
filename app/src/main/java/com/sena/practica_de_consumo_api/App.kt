package com.sena.practica_de_consumo_api

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sena.practica_de_consumo_api.presentation.user.PokemonScreen
import com.sena.practica_de_consumo_api.screens.LoginScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){
    LoginScreen()
}

/**
@Composable
fun UserScreen(modifier: Modifier = Modifier) {
    Text(modifier = modifier,
        text = "Hola")
}
        **/
