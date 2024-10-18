package com.seisme.dimas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seisme.dimas.ui.screens.homeScreen.HomeScreen
import com.seisme.dimas.ui.screens.loginScreen.LoginScreen
import com.seisme.dimas.ui.screens.mitigasiScreen.MitigasiScreen
import com.seisme.dimas.ui.screens.petaScreen.PetaScreen
import com.seisme.dimas.ui.screens.profileScreen.ProfileScreen
import com.seisme.dimas.ui.screens.registerScreen.RegisterScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineDetailScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val isLoggedIn = remember { mutableStateOf(false) } // Login state

    NavHost(navController = navController, startDestination = if (isLoggedIn.value) Routes.Home.route else Routes.Login.route) {

        // Login Screen
        composable(Routes.Login.route) {
            LoginScreen(onNavigateToHome = {
                // On successful login, set login state to true and navigate to Home
                isLoggedIn.value = true
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Login.route) { inclusive = true } // Remove login screen from back stack
                }
            }, onNavigateToRegister = {
                navController.navigate(Routes.Register.route)
            })
        }

        // Register Screen
        composable(Routes.Register.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Register.route) { inclusive = true } // Remove register screen from back stack
                }
            })
        }

        // Home Screen
        composable(Routes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Routes.Peta.route) { PetaScreen() }
        composable(Routes.Timeline.route) { TimelineScreen(navController) }
        composable(Routes.Mitigasi.route) { MitigasiScreen() }
        composable(Routes.Profil.route) { ProfileScreen() }
        composable(Routes.TimelineDetail.route) { backStackEntry ->
            TimelineDetailScreen(
                tanggal = backStackEntry.arguments?.getString("tanggal") ?: "",
                wilayah = backStackEntry.arguments?.getString("wilayah") ?: "",
                magnitudo = backStackEntry.arguments?.getString("magnitudo") ?: "",
                coordinates = backStackEntry.arguments?.getString("coordinates") ?: "",
                kedalaman = backStackEntry.arguments?.getString("kedalaman") ?: ""
            )
        }
    }
}
