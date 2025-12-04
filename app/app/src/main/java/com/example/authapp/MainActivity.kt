package com.example.authapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.authapp.ui.navigation.NavGraph
import com.example.authapp.ui.navigation.Screen
import com.example.authapp.ui.theme.AuthAppTheme
import com.example.authapp.utils.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tokenManager = TokenManager(this)
        val startDestination = if (tokenManager.isLoggedIn()) {
            Screen.Home.route
        } else {
            Screen.Login.route
        }

        setContent {
            AuthAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
