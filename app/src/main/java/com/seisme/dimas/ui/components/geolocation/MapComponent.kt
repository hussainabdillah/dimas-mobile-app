package com.seisme.dimas.ui.components.geolocation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.seisme.dimas.data.model.EarthquakeData
import com.seisme.dimas.data.model.ShakeReport

@Composable
fun MapComponent(
    earthquakeData: EarthquakeData? = null,
    userLocation: LatLng? = null,
    shakingReports: List<ShakeReport>? = null,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            earthquakeData?.coordinates ?: LatLng(0.0, 0.0),
            10f
        )
    }

    // Update camera position to earthquake coordinates when they are available
    LaunchedEffect(earthquakeData?.coordinates) {
        earthquakeData?.coordinates?.let { coordinates ->
            cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(coordinates, 10f))
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
        )
    ) {
        userLocation?.let {
            MarkerUser(it)
        }
        earthquakeData?.let {
            MarkerEarthquake(it)
        }
        shakingReports?.forEach { location ->
            MarkerShakingReport(location)
        }
    }
}
