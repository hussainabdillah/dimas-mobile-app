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
import com.seisme.dimas.data.repository.EarthquakeData
import com.seisme.dimas.ui.theme.GreenPin
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.PurplePin
import com.seisme.dimas.ui.theme.RedPin
import com.seisme.dimas.ui.theme.YellowPin

@Composable
fun MarkerEarthquake(earthquakeData: EarthquakeData) {
    val magnitudeColor = getMagnitudeColor(earthquakeData.magnitude)

    MarkerComposable(
        state = rememberMarkerState(position = earthquakeData.location),
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

fun getMagnitudeColor(magnitude: Int): Color {
    return when (magnitude) {
        1 -> LightBlue
        2, 3, 4 -> GreenPin
        5, 6 -> YellowPin
        7, 8 -> RedPin
        else -> PurplePin
    }
}

