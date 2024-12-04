package com.seisme.dimas.ui.navigation

import okhttp3.Route

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object AdditionalRegister : Routes("additional_register?email={email}&password={password}")

    object Map : Routes("map")

    object Timeline : Routes("timeline")
    object TimelineDetail : Routes("timelineDetail/{date}/{time}/{location}/{magnitude}/{depth}/{coordinates}/")

    object Profile : Routes("profile")
    object AddMember : Routes("addMember")
    object ListMember: Routes("listMember")
    object Setting : Routes("setting")
    object EarthquakeMitigation : Routes("earthquakeMitigation")
    object TsunamiMitigation : Routes("tsunamiMitigation")
    object ShakingReportScreen : Routes("shakingReport")

    object Mitigation : Routes("mitigation")

    object Splash : Routes ("splash")
}