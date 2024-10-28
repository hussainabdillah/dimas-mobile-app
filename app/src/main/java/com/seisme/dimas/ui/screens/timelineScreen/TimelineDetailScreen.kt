package com.seisme.dimas.ui.screens.timelineScreen

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.LatLng
import com.seisme.dimas.R
import com.seisme.dimas.data.repository.EarthquakeData
import com.seisme.dimas.ui.components.geolocation.MapComponent
import com.seisme.dimas.ui.components.item.InformationDetail
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TimelineDetailScreen(
    date: String,
    time: String,
    location: String,
    magnitude: String,
    coordinates: String,
    depth: String,
    navController: NavHostController
) {
    val parsedCoordinates = coordinates.split(",").let {
        LatLng(it[0].toDouble(), it[1].toDouble())
    }

    val parsedMagnitude = magnitude.toFloatOrNull() ?: 0f

    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.header_detail_informasi),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Routes.Timeline.route) {
                        popUpTo(Routes.Timeline.route)
                    }
                },
                isIconAtStart = true
            )
        },
    ) { _ ->
        MapComponent(
            earthquakeData = EarthquakeData(parsedCoordinates, parsedMagnitude.toInt()),
        )
        InformationDetail(
            date = date,
            time = time,
            location = location,
            magnitude = magnitude,
            depth = depth
        )
    }
}
