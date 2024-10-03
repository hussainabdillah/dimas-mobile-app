package com.seisme.dimas.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Mitigasi : Routes("mitigasi")
    object Notification : Routes("notification")
    object Peta : Routes("peta")
    object Profil : Routes("profil")
}