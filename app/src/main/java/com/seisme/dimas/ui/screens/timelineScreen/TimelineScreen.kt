package com.seisme.dimas.ui.screens.timelineScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.Header
import com.seisme.dimas.ui.components.BottomNavigationBar
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.screens.MitigasiScreen.MitigasiScreen
import com.seisme.dimas.ui.screens.NotificationScreen.NotificationScreen
import com.seisme.dimas.ui.screens.PetaScreen.PetaScreen
import com.seisme.dimas.ui.screens.ProfilScreen.ProfilScreen

@Composable
fun TimelineScreen() {
    val navigationController = rememberNavController()

    Scaffold(
        topBar = {
            Header(title = stringResource(R.string.timeline_header))
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navigationController)
        }
    ) { padding ->
        // Function to collect active state from icon
        Box(
            modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            NavHost(
                navController = navigationController,
                startDestination = Routes.Peta.route
            ) {
                composable(Routes.Peta.route) { PetaScreen() }
                composable(Routes.Mitigasi.route) { MitigasiScreen() }
                composable(Routes.Notification.route) { NotificationScreen() }
                composable(Routes.Profil.route) { ProfilScreen() }
            }

        }
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .absoluteOffset(x = 130.dp, y = 0.dp)
                .background(Color.LightGray)
        )
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 20.dp,
                    end = 20.dp,
                    bottom = 20.dp
                )
        ) {
            items(10) {
                EarthquakeItem(
                    time = "14.41",
                    date = "20 Sept 2019",
                    location = "Selatan Gunung Semeru",
                    magnitude = "2 Sr"
                )
            }
        }
    }
}

@Composable
fun EarthquakeItem(time: String, date: String, location: String, magnitude: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(text = time, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = date, fontSize = 10.sp, fontWeight = FontWeight.Light)
        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            shadowElevation = 3.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = location, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Row {
                        Text(
                            text = "Magnitudo",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text(
                            text = magnitude,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Navigate",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimelineScreenPreview() {
    TimelineScreen()
}
