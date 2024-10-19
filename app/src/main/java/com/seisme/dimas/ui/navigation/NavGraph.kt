package com.seisme.dimas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seisme.dimas.ui.screens.loginScreen.LoginScreen
import com.seisme.dimas.ui.screens.mitigationScreen.MitigationScreen
import com.seisme.dimas.ui.screens.mapScreen.MapScreen
import com.seisme.dimas.ui.screens.profileScreen.ProfileScreen
import com.seisme.dimas.ui.screens.profileScreen.SettingScreen
import com.seisme.dimas.ui.screens.registerScreen.RegisterScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineDetailScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val isLoggedIn = remember { mutableStateOf(true) }

    NavHost(navController = navController, startDestination = if (isLoggedIn.value) Routes.Map.route else Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginScreen(onNavigateToHome = {
                isLoggedIn.value = true
                navController.navigate(Routes.Map.route) {
                    popUpTo(Routes.Login.route) { inclusive = true }
                }
            }, onNavigateToRegister = {
                navController.navigate(Routes.Register.route)
            })
        }

        // Register Screen
        composable(Routes.Register.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Register.route) { inclusive = true }
                }
            })
        }

        // Home Screen
        composable(Routes.Map.route) { MapScreen(navController) }
        composable(Routes.Timeline.route) { TimelineScreen(navController) }
        composable(Routes.Mitigation.route) { MitigationScreen(navController) }
        composable(Routes.Profile.route) { ProfileScreen(navController) }
        composable(Routes.TimelineDetail.route) { backStackEntry ->
            TimelineDetailScreen(
                tanggal = backStackEntry.arguments?.getString("tanggal") ?: "",
                wilayah = backStackEntry.arguments?.getString("wilayah") ?: "",
                magnitudo = backStackEntry.arguments?.getString("magnitudo") ?: "",
                coordinates = backStackEntry.arguments?.getString("coordinates") ?: "",
                kedalaman = backStackEntry.arguments?.getString("kedalaman") ?: "",
                navController = navController
            )
        }
        composable(Routes.Setting.route) { SettingScreen(navController) }
    }
}
