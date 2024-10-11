package com.seisme.dimas.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_peta),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == Routes.Peta.route) SkyBlue else Gray
                )
                Text(
                    text = "Peta",
                    fontSize = 12.sp,
                    color = if (currentRoute == Routes.Peta.route) SkyBlue else Gray,  // Update text color
                    textAlign = TextAlign.Center
                )
            }
        }

        // Timeline Icon
        IconButton(
            onClick = {
                navigationController.navigate(Routes.Timeline.route) {
                    popUpTo(Routes.Timeline.route)
                }
            },
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_timeline),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == Routes.Timeline.route) SkyBlue else Gray
                )
                Text(
                    text = "Timeline",
                    fontSize = 12.sp,
                    color = if (currentRoute == Routes.Timeline.route) SkyBlue else Gray,  // Update text color
                    textAlign = TextAlign.Center
                )
            }
        }

        // Mitigasi Icon
        IconButton(
            onClick = {
                navigationController.navigate(Routes.Mitigasi.route) {
                    popUpTo(Routes.Mitigasi.route)
                }
            },
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mitigasi),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == Routes.Mitigasi.route) SkyBlue else Gray
                )
                Text(
                    text = "Mitigasi",
                    fontSize = 12.sp,
                    color = if (currentRoute == Routes.Mitigasi.route) SkyBlue else Gray,  // Update text color
                    textAlign = TextAlign.Center
                )
            }
        }

        // Profile Icon
        IconButton(
            onClick = {
                navigationController.navigate(Routes.Profil.route) {
                    popUpTo(Routes.Profil.route)
                }
            },
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == Routes.Profil.route) SkyBlue else Gray
                )
                Text(
                    text = "Profil",
                    fontSize = 12.sp,
                    color = if (currentRoute == Routes.Profil.route) SkyBlue else Gray,  // Update text color
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}