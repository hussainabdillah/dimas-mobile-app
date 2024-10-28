package com.seisme.dimas.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")

    object Map : Routes("map")

    object Timeline : Routes("timeline")
    object TimelineDetail : Routes("timelineDetail/{date}/{time}/{location}/{magnitude}/{depth}/{coordinates}/")

    object Profile : Routes("profile")
    object AddMember : Routes("addMember")
    object Setting : Routes("setting")
    object EarthquakeMitigation : Routes("earthquakeMitigation")
    object TsunamiMitigation : Routes("tsunamiMitigation")
    object ShakingReportScreen : Routes("shakingReport")

    object Mitigation : Routes("mitigation")
}