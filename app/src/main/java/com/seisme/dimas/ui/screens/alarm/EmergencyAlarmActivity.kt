package com.seisme.dimas.ui.screens.alarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seisme.dimas.ui.screens.mapScreen.ShakeReportScreen
import com.seisme.dimas.ui.screens.mitigationScreen.MitigationScreen

class EmergencyAlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmergencyAlarmApp()
        }
    }
}

@Composable
fun EmergencyAlarmApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "emergency_alarm_screen"
    ) {
        composable("emergency_alarm_screen") {
            EmergencyAlarmScreen(
                onMitigasiClick = { navController.navigate("mitigation_screen") },
                onReportClick = { navController.navigate("shake_report_screen") }
            )
        }
        composable("mitigation_screen") {
            MitigationScreen(navController = navController) // Meneruskan NavHostController
        }
        composable("shake_report_screen") {
            ShakeReportScreen() // Pastikan ShakeReportScreen tidak memerlukan NavHostController
        }
    }
}
