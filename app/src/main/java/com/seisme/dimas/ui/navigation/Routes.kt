package com.seisme.dimas.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Timeline : Routes("timeline")
    object Mitigasi : Routes("mitigasi")
    object Peta : Routes("peta")
    object Profil : Routes("profil")
}