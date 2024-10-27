package com.seisme.dimas.ui.screens.mapScreen

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Header(
                title = "Terjadi pada 9/10 12:27",
            )
        },
        floatingActionButton = {
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
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                IconButton(
                    onClick = { navController.navigate("shakingreport") },
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
                        modifier = Modifier
                            .size(28.dp)
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) { _ ->
        val userLocation: LatLng = getUserLocation()
        val earthquakeData: EarthquakeData = getLatestEarthquakeLocation()
        val shakingReports: List<ShakingReportData> = getShakingReport()

        MapComponent(
            modifier = Modifier.fillMaxSize(),
            userLocation = userLocation,
            earthquakeData = earthquakeData,
            shakingReports = shakingReports
        )
    }
}