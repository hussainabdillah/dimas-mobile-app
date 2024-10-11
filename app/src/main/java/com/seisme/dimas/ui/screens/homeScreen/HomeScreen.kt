package com.seisme.dimas.ui.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seisme.dimas.ui.components.BottomNavigationBar
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.screens.petaScreen.PetaScreen
import com.seisme.dimas.ui.screens.profilScreen.ProfilScreen
import com.seisme.dimas.ui.screens.timelineScreen.TimelineScreen
import com.seisme.dimas.ui.screens.mitigasiScreen.MitigasiScreen
import com.seisme.dimas.ui.theme.DimasTheme

@Composable
fun HomeScreen() {
    val navigationController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationController = navigationController)
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            NavHost(
                navController = navigationController,
                startDestination = Routes.Peta.route
            ) {
                composable(Routes.Peta.route) { PetaScreen() }
                composable(Routes.Timeline.route) { TimelineScreen() }
                composable(Routes.Mitigasi.route) { MitigasiScreen() }
                composable(Routes.Profil.route) { ProfilScreen() }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DimasTheme {
        HomeScreen()
    }
}