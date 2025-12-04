package com.example.authapp.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authapp.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = mutableStateOf<AuthState>(AuthState.Idle)
    val authState: State<AuthState> = _authState

    private val _loginUiState = mutableStateOf(LoginUiState())
    val loginUiState: State<LoginUiState> = _loginUiState

    private val _registerUiState = mutableStateOf(RegisterUiState())
    val registerUiState: State<RegisterUiState> = _registerUiState

    // Login Functions
    fun updateLoginEmail(email: String) {
        _loginUiState.value = _loginUiState.value.copy(
            email = email,
            emailError = null
        )
    }

    fun updateLoginPassword(password: String) {
        _loginUiState.value = _loginUiState.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun toggleLoginPasswordVisibility() {
        _loginUiState.value = _loginUiState.value.copy(
            isPasswordVisible = !_loginUiState.value.isPasswordVisible
        )
    }

    fun login() {
        val email = _loginUiState.value.email
        val password = _loginUiState.value.password

        // Validación
        if (!validateLoginInputs(email, password)) return

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            repository.login(email, password)
                .onSuccess { response ->
                    _authState.value = AuthState.Success(response.user, response.token)
                }
                .onFailure { error ->
                    _authState.value = AuthState.Error(
                        error.message ?: "Error al iniciar sesión"
                    )
                }
        }
    }

    private fun validateLoginInputs(email: String, password: String): Boolean {
        var isValid = true

        if (email.isBlank()) {
            _loginUiState.value = _loginUiState.value.copy(
                emailError = "El email es requerido"
            )
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _loginUiState.value = _loginUiState.value.copy(
                emailError = "Email inválido"
            )
            isValid = false
        }

        if (password.isBlank()) {
            _loginUiState.value = _loginUiState.value.copy(
                passwordError = "La contraseña es requerida"
            )
            isValid = false
        }

        return isValid
    }

    // Register Functions
    fun updateRegisterUsername(username: String) {
        _registerUiState.value = _registerUiState.value.copy(
            username = username,
            usernameError = null
        )
    }

    fun updateRegisterEmail(email: String) {
        _registerUiState.value = _registerUiState.value.copy(
            email = email,
            emailError = null
        )
    }

    fun updateRegisterPassword(password: String) {
        _registerUiState.value = _registerUiState.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun toggleRegisterPasswordVisibility() {
        _registerUiState.value = _registerUiState.value.copy(
            isPasswordVisible = !_registerUiState.value.isPasswordVisible
        )
    }

    fun register() {
        val username = _registerUiState.value.username
        val email = _registerUiState.value.email
        val password = _registerUiState.value.password

        // Validación
        if (!validateRegisterInputs(username, email, password)) return

        _authState.value = AuthState.Loading

        viewModelScope.launch {
            repository.register(username, email, password)
                .onSuccess { response ->
                    _authState.value = AuthState.Success(response.user, response.token)
                }
                .onFailure { error ->
                    _authState.value = AuthState.Error(
                        error.message ?: "Error al registrarse"
                    )
                }
        }
    }

    private fun validateRegisterInputs(
        username: String,
        email: String,
        password: String
    ): Boolean {
        var isValid = true

        if (username.isBlank()) {
            _registerUiState.value = _registerUiState.value.copy(
                usernameError = "El nombre de usuario es requerido"
            )
            isValid = false
        }

        if (email.isBlank()) {
            _registerUiState.value = _registerUiState.value.copy(
                emailError = "El email es requerido"
            )
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _registerUiState.value = _registerUiState.value.copy(
                emailError = "Email inválido"
            )
            isValid = false
        }

        if (password.isBlank()) {
            _registerUiState.value = _registerUiState.value.copy(
                passwordError = "La contraseña es requerida"
            )
            isValid = false
        } else if (password.length < 6) {
            _registerUiState.value = _registerUiState.value.copy(
                passwordError = "La contraseña debe tener al menos 6 caracteres"
            )
            isValid = false
        }

        return isValid
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }
}
