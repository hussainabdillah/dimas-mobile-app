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
import com.seisme.dimas.data.repository.ShakingReportData
import com.seisme.dimas.ui.theme.GreenPin
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.PurplePin
import com.seisme.dimas.ui.theme.RedPin
import com.seisme.dimas.ui.theme.YellowPin

@Composable
fun MarkerShakingReport(shakingReportData: ShakingReportData) {
    val shakingReportColor = getShakePowerColor(shakingReportData.shakePower)

    MarkerComposable(
        state = rememberMarkerState(position = shakingReportData.location),
        title = "Shaking Report",
        snippet = "Reported ${shakingReportData.shakePower}"
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = shakingReportColor.copy(alpha = 0.2f),
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
                    painter = painterResource(id = R.drawable.ic_peta),
                    contentDescription = null,
                    tint = shakingReportColor,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

fun getShakePowerColor(shakePower: Int): Color {
    return when (shakePower) {
        1 -> LightBlue
        2 -> GreenPin
        3 -> YellowPin
        4 -> RedPin
        else -> PurplePin
    }
}

