package com.seisme.dimas.ui.screens.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar

@Composable
fun HomeScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    DimasTheme {
//        HomeScreen()
//    }
//}