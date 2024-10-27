package com.seisme.dimas.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")

    object Map : Routes("map")

    object Timeline : Routes("timeline")
    object TimelineDetail : Routes("timelineDetail/{date}/{location}/{magnitude}/{coordinates}/{depth}")

    object Profile : Routes("profile")
    object AddMember : Routes("addMember")
    object Setting : Routes("setting")
    object EarthquakeMitigation : Routes("earthquakemitigation")
    object TsunamiMitigation : Routes("tsunamimitigation")
    object ShakingReportScreen : Routes("shakingreport")

    object Mitigation : Routes("mitigation")
}