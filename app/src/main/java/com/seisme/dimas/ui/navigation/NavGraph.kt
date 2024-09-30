package com.seisme.dimas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seisme.dimas.ui.screens.HomeScreen.HomeScreen
import com.seisme.dimas.ui.screens.LoginScreen.LoginScreen
import com.seisme.dimas.ui.screens.RegisterScreen.RegisterScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(onNavigateToHome = {
                navController.navigate(Routes.Home.route)
            }, onNavigateToRegister = {
                navController.navigate(Routes.Register.route)
            })
        }
        composable(Routes.Register.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(Routes.Login.route)
            })
        }
        composable(Routes.Home.route) {
            HomeScreen()
        }
    }
}