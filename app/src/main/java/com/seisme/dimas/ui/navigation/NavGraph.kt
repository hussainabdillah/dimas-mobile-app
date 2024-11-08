package com.seisme.dimas.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.seisme.dimas.R
import com.seisme.dimas.ui.screens.loginScreen.LoginScreen
import com.seisme.dimas.ui.screens.mitigationScreen.MitigationScreen
import com.seisme.dimas.ui.screens.mapScreen.MapScreen
import com.seisme.dimas.ui.screens.mitigationScreen.EarthquakeMitigationScreen
import com.seisme.dimas.ui.screens.mitigationScreen.TsunamiMitigationScreen
import com.seisme.dimas.ui.screens.profileScreen.ProfileScreen
import com.seisme.dimas.ui.screens.profileScreen.SettingScreen
import com.seisme.dimas.ui.screens.profileScreen.addMemberScreen.AddMemberScreen
import com.seisme.dimas.ui.screens.registerScreen.RegisterScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineDetailScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineScreen
import com.seisme.dimas.ui.screens.mapScreen.ShakeReportScreen
import com.seisme.dimas.ui.screens.registerScreen.AdditionalRegisterScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val isLoggedIn = remember { mutableStateOf(sharedPreferences.getBoolean("isLoggedIn", false)) }

    NavHost(navController = navController, startDestination = if (isLoggedIn.value) Routes.Map.route else Routes.Login.route) {

        // Login Screen
        composable(Routes.Login.route) {
            LoginScreen(onNavigateToHome = {
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
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
            RegisterScreen(
                onNavigateToLogin = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Register.route) { inclusive = true }
                }
            },
                navController = navController
            )
        }

        // Additional Register Screen
        composable(Routes.AdditionalRegister.route) {
            AdditionalRegisterScreen(
                onNavigateToLogin = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Register.route) { inclusive = true }
                }
            },
                navController = navController
            )
        }

        // Home Screen
        composable(Routes.Map.route) { MapScreen(navController) }

        composable(Routes.Timeline.route) { TimelineScreen(navController) }
        composable(Routes.TimelineDetail.route) { backStackEntry ->
            TimelineDetailScreen(
                date = backStackEntry.arguments?.getString("date") ?: "",
                time = backStackEntry.arguments?.getString("time") ?: "",
                location = backStackEntry.arguments?.getString("location") ?: "",
                magnitude = backStackEntry.arguments?.getString("magnitude") ?: "",
                depth = backStackEntry.arguments?.getString("depth") ?: "",
                coordinates = backStackEntry.arguments?.getString("coordinates") ?: "",
                region = backStackEntry.arguments?.getString("region") ?: "",
                felt = backStackEntry.arguments?.getString("felt") ?: "",
                navController = navController
            )
        }

        composable(Routes.Profile.route) { ProfileScreen(navController) }
        composable(Routes.AddMember.route) { AddMemberScreen(navController) }
        composable(Routes.Setting.route) { SettingScreen(navController) }
        composable(Routes.EarthquakeMitigation.route) { EarthquakeMitigationScreen(navController) }
        composable(Routes.TsunamiMitigation.route) { TsunamiMitigationScreen(navController) }
        composable(Routes.ShakingReportScreen.route) { ShakeReportScreen()}

        composable(Routes.Mitigation.route) { MitigationScreen(navController) }
        composable(Routes.EarthquakeMitigation.route) { EarthquakeMitigationScreen(navController) }
        composable(Routes.TsunamiMitigation.route) { TsunamiMitigationScreen(navController) }
    }
}
