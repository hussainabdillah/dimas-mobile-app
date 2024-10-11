package com.seisme.dimas.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seisme.dimas.R
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.Gray
import com.seisme.dimas.ui.theme.SkyBlue

@Composable
fun BottomNavigationBar(navigationController: NavHostController) {
    val navBackStackEntry = navigationController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    BottomAppBar(
        containerColor = Color.White,
        modifier = Modifier.border(width = 1.dp, color = Color.LightGray)
    ) {
        // Peta Icon
        IconButton(
            onClick = {
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
                tint = if (currentRoute == Routes.Peta.route) SkyBlue else Gray
            )
        }

        // Mitigasi Icon
        IconButton(
            onClick = {
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
                tint = if (currentRoute == Routes.Mitigasi.route) SkyBlue else Gray
            )
        }

        // Notification Icon
        IconButton(
            onClick = {
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
                tint = if (currentRoute == Routes.Notification.route) SkyBlue else Gray
            )
        }

        // Profile Icon
        IconButton(
            onClick = {
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
                tint = if (currentRoute == Routes.Profil.route) SkyBlue else Gray
            )
        }
    }
}
