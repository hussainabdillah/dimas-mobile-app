package com.seisme.dimas.ui.screens.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seisme.dimas.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun loginWithEmailAndPassword() {
        viewModelScope.launch {
            try {
                authRepository.loginWithEmail(_state.value.email, _state.value.password)
                // Update state untuk menunjukkan bahwa pengguna sudah login
                _state.value = _state.value.copy(isLoggedIn = true, errorMessage = null)
                // Handle navigasi ke Home Screen, jika menggunakan Event atau Callback
            } catch (e: Exception) {
                // Handle error, misalnya dengan menunjukkan pesan kesalahan
                _state.value = _state.value.copy(errorMessage = "Email atau password salah.")
            }
        }
    }

    fun loginWithGoogle() {
        // Integrate Google Sign-In and get the token
        // Then pass the token to authRepository.loginWithGoogle(idToken)
    }
}
