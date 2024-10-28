package com.seisme.dimas.ui.screens.mapScreen

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.seisme.dimas.data.repository.EarthquakeData
import com.seisme.dimas.data.repository.ShakingReportData
import com.seisme.dimas.data.repository.getLatestEarthquakeLocation
import com.seisme.dimas.data.repository.getShakingReport
import com.seisme.dimas.data.repository.getUserLocation
import com.seisme.dimas.ui.components.geolocation.MapComponent
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.theme.Orange
import com.seisme.dimas.ui.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavHostController) {
    val dropdownVisibility = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        Header(
            // Perlu dibenahi menjadi datetime gempa terbaru
            title = "Terjadi pada 9/10 12:27",
        )
    }, floatingActionButton = {
        if (!dropdownVisibility.value) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(54.dp)
                        .shadow(3.dp, RoundedCornerShape(100), clip = true)
                        .clip(RoundedCornerShape(100))
                        .background(White),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                }
                IconButton(
                    onClick = {
                        dropdownVisibility.value = !dropdownVisibility.value
                    },
                    modifier = Modifier
                        .size(54.dp)
                        .shadow(3.dp, RoundedCornerShape(100), clip = true)
                        .clip(RoundedCornerShape(100))
                        .background(Orange),
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Warning,
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }, bottomBar = {
        BottomNavigationBar(navigationController = navController)
    }) { padding ->
        val userLocation: LatLng = getUserLocation()
        val earthquakeData: EarthquakeData = getLatestEarthquakeLocation()
        val shakingReports: List<ShakingReportData> = getShakingReport()

        MapComponent(
            userLocation = userLocation,
            earthquakeData = earthquakeData,
            shakingReports = shakingReports
        )

        AnimatedVisibility(
            modifier = Modifier.padding(padding),
            visible = dropdownVisibility.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )
        }

        AnimatedVisibility(
            modifier = Modifier.padding(padding),
            visible = dropdownVisibility.value,
            enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight }),
            exit = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight })
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .clickable { dropdownVisibility.value = false },
                contentAlignment = Alignment.BottomCenter
            ) {
                ShakeReportScreen(onCloseClick = { dropdownVisibility.value = false })
            }
        }
    }
}