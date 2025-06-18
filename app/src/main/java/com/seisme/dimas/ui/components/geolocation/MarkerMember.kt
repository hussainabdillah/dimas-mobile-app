package com.seisme.dimas.ui.components.geolocation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberMarkerState
import com.seisme.dimas.R
import com.seisme.dimas.data.model.UserData
import com.seisme.dimas.ui.theme.BluePin
import com.seisme.dimas.ui.theme.GreenPin
import com.seisme.dimas.ui.theme.PurplePin
import com.seisme.dimas.ui.theme.White

@Composable
fun MarkerMember(memberData: UserData) {
    fun GeoPoint.toLatLng(): LatLng {
        return LatLng(this.latitude, this.longitude)
    }
    MarkerComposable(
        state = rememberMarkerState(position = memberData.location.toLatLng()),
        title = "Lokasi " + memberData.username,
        snippet = "Safe"
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = BluePin.copy(alpha = 0.2f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        White,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    tint = BluePin,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}