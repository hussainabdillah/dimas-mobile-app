package com.seisme.dimas.ui.components.geolocation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.seisme.dimas.data.repository.EarthquakeData
import com.seisme.dimas.data.repository.ShakingReportData

@Composable
fun MapComponent(
    earthquakeData: EarthquakeData? = null,
    userLocation: LatLng? = null,
    shakingReports: List<ShakingReportData>? = null,
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            earthquakeData?.location ?: LatLng(0.0, 0.0),
            10f
        )
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
