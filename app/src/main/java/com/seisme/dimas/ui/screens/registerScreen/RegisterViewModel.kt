package com.seisme.dimas.ui.screens.registerScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seisme.dimas.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun registerWithEmailAndPassword() {
        viewModelScope.launch {
            try {
                authRepository.registerWithEmail(_state.value.email, _state.value.password)
                // Handle success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun loginWithGoogle() {
        // Integrate Google Sign-In and get the token
        // Then pass the token to authRepository.loginWithGoogle(idToken)
    }
}
