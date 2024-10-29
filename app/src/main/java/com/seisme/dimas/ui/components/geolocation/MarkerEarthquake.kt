package com.seisme.dimas.ui.components.geolocation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberMarkerState
import com.seisme.dimas.R
import com.seisme.dimas.data.model.EarthquakeData
import com.seisme.dimas.ui.theme.GreenPin
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.PurplePin
import com.seisme.dimas.ui.theme.RedPin
import com.seisme.dimas.ui.theme.YellowPin

@Composable
fun MarkerEarthquake(earthquakeData: EarthquakeData) {
    val magnitudeColor = getMagnitudeColor(earthquakeData.magnitude)

    MarkerComposable(
        state = rememberMarkerState(position = earthquakeData.coordinates),
        title = "Earthquake Location",
        snippet = "Magnitude: ${earthquakeData.magnitude}"
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = magnitudeColor.copy(alpha = 0.2f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        Color.White,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_mitigasi),
                    contentDescription = null,
                    tint = magnitudeColor,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

fun getMagnitudeColor(magnitude: Double): Color {
    return when {
        magnitude == 1.0 -> LightBlue
        magnitude in 2.0..4.9 -> GreenPin
        magnitude in 5.0..6.9 -> YellowPin
        magnitude in 7.0..8.9 -> RedPin
        else -> PurplePin
    }
}

