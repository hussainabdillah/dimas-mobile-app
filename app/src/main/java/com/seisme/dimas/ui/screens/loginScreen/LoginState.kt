package com.seisme.dimas.ui.screens.loginScreen

data class LoginState(
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false // Menyimpan status login
)