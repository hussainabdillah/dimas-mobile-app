package com.seisme.dimas.ui.screens.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seisme.dimas.R
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.screens.MitigasiScreen.MitigasiScreen
import com.seisme.dimas.ui.screens.NotificationScreen.NotificationScreen
import com.seisme.dimas.ui.screens.PetaScreen.PetaScreen
import com.seisme.dimas.ui.screens.ProfilScreen.ProfilScreen
import com.seisme.dimas.ui.theme.DimasTheme
import com.seisme.dimas.ui.theme.Gray
import com.seisme.dimas.ui.theme.SkyBlue

@Composable
fun HomeScreen() {
    val navigationController = rememberNavController() // Set up the NavController here

    Scaffold(
        bottomBar = {
            MyBottomAppBar(navigationController = navigationController) // Pass NavController to the bottom app bar
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            NavHost(
                navController = navigationController,
                startDestination = Routes.Peta.route // Set the default route
            ) {
                composable(Routes.Peta.route) { PetaScreen() }
                composable(Routes.Mitigasi.route) { MitigasiScreen() }
                composable(Routes.Notification.route) { NotificationScreen() }
                composable(Routes.Profil.route) { ProfilScreen() }
            }
        }
    }
}

@Composable
fun MyBottomAppBar(navigationController: NavHostController) {
    // Track which icon is selected
    val selected = remember { mutableStateOf(R.drawable.ic_peta) }

    BottomAppBar(
        containerColor = Color.White // Set the background color of the BottomAppBar
    ) {
        // Peta Icon
        IconButton(
            onClick = {
                selected.value = R.drawable.ic_peta
                navigationController.navigate(Routes.Peta.route) {
                    popUpTo(Routes.Peta.route)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_peta),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                // Apply custom blue if selected, gray if not
                tint = if (selected.value == R.drawable.ic_peta) SkyBlue else Gray
            )
        }

        // Mitigasi Icon
        IconButton(
            onClick = {
                selected.value = R.drawable.ic_mitigasi
                navigationController.navigate(Routes.Mitigasi.route) {
                    popUpTo(Routes.Mitigasi.route)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mitigasi),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = if (selected.value == R.drawable.ic_mitigasi) SkyBlue else Gray
            )
        }

        // Notification Icon
        IconButton(
            onClick = {
                selected.value = R.drawable.ic_notifikasi
                navigationController.navigate(Routes.Notification.route) {
                    popUpTo(Routes.Notification.route)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifikasi),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = if (selected.value == R.drawable.ic_notifikasi) SkyBlue else Gray
            )
        }

        // Profile Icon
        IconButton(
            onClick = {
                selected.value = R.drawable.ic_profil
                navigationController.navigate(Routes.Profil.route) {
                    popUpTo(Routes.Profil.route)
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_profil),
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = if (selected.value == R.drawable.ic_profil) SkyBlue else Gray
            )
        }
    }
}

@Preview
@Composable
fun MyBottomBarPreview() {
    DimasTheme {
        val mockNavController = rememberNavController() // Create a mock NavController
        MyBottomAppBar(navigationController = mockNavController) // Pass the mock controller
    }
}