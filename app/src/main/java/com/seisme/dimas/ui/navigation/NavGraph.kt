package com.seisme.dimas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seisme.dimas.ui.screens.HomeScreen.HomeScreen
import com.seisme.dimas.ui.screens.LoginScreen.LoginScreen
import com.seisme.dimas.ui.screens.RegisterScreen.RegisterScreen

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
            HomeScreen()
        }
    }
}
