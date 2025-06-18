package com.seisme.dimas.ui.screens.registerScreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
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

    fun onConfirmationPasswordChanged(confirmationPassword: String) {
        _state.value = _state.value.copy(confirmationPassword = confirmationPassword)
    }

    fun onUsernameChanged(username: String) {
        _state.value = _state.value.copy(username = username)
    }

    fun onContactChanged(contact: String) {
        _state.value = _state.value.copy(contact = contact)
    }

    fun onGenderChanged(gender: String) {
        _state.value = _state.value.copy(gender = gender)
    }


    fun completeRegistration(
        email: String,
        password: String,
        username: String,
        contact: String,
        gender: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Simpan data tambahan ke Firestore
                    val userId = Firebase.auth.currentUser?.uid ?: return@addOnCompleteListener
                    val user = hashMapOf(
                        "username" to username,
                        "contact" to contact,
                        "gender" to gender,
                        "email" to email
                    )
                    Firebase.firestore.collection("users").document(userId).set(user)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { exception -> onError(exception.message ?: "Failed to register") }
                } else {
                    onError(task.exception?.message ?: "Failed to create account")
                }
            }
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
