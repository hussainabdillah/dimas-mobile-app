package com.seisme.dimas.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Map : Routes("map")
    object Timeline : Routes("timeline")
    object TimelineDetail : Routes("timelineDetail/{tanggal}/{pusatGempa}/{magnitudo}/{intensitas}/{kedalaman}")
    object Mitigation : Routes("mitigation")
    object Profile : Routes("profile")
    object Setting : Routes("setting")
}