package com.example.authapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authapp.ui.components.CustomButton
import com.example.authapp.ui.components.CustomTextField
import com.example.authapp.ui.components.CustomTextButton
import com.example.authapp.utils.TokenManager
import com.example.authapp.viewmodel.AuthState
import com.example.authapp.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    val context = LocalContext.current
    val tokenManager = TokenManager(context)

    val authState = viewModel.authState.value
    val uiState = viewModel.registerUiState.value

    val accentColor = Color(0xFFBB86FC)

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                tokenManager.saveToken(authState.token)
                tokenManager.saveUserData(
                    authState.user.id,
                    authState.user.username,
                    authState.user.email
                )
                Toast.makeText(
                    context,
                    "¡Cuenta creada!",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetAuthState()
                onRegisterSuccess()
            }
            is AuthState.Error -> {
                Toast.makeText(
                    context,
                    authState.message,
                    Toast.LENGTH_LONG
                ).show()
                viewModel.resetAuthState()
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F1B24))
            .padding(horizontal = 32.dp, vertical = 40.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Registro",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )




            CustomTextField(
                value = uiState.username,
                onValueChange = { viewModel.updateRegisterUsername(it) },
                label = "Nombre",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Username Icon",
                        tint = accentColor
                    )
                },
                errorMessage = uiState.usernameError,
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateRegisterEmail(it) },
                label = "Email",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = accentColor
                    )
                },
                errorMessage = uiState.emailError,
                keyboardType = KeyboardType.Email
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = uiState.password,
                onValueChange = { viewModel.updateRegisterPassword(it) },
                label = "Password",
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = accentColor
                    )
                },
                isPassword = true,
                isPasswordVisible = uiState.isPasswordVisible,
                onPasswordVisibilityToggle = { viewModel.toggleRegisterPasswordVisibility() },
                errorMessage = uiState.passwordError,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomButton(
                text = "REGISTRARSE",
                onClick = { viewModel.register() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                isLoading = authState is AuthState.Loading,
                icon = Icons.Default.PersonAdd,
                enabled = authState !is AuthState.Loading
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextButton(
                text = " Ya tengo cuenta",
                onClick = onNavigateToLogin,
                isLoading = false
            )
        }
    }
}