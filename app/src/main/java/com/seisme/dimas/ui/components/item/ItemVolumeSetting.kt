package com.seisme.dimas.ui.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seisme.dimas.R
import com.seisme.dimas.ui.theme.Gray
import com.seisme.dimas.ui.theme.LightBlue

@Composable
fun ItemVolumeSetting(
    imgResId: Int,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Image(
            painter = painterResource(id = imgResId),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .padding(end = 8.dp)
        )

        Slider(
            value = value,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                thumbColor = LightBlue,
                activeTrackColor = LightBlue,
                inactiveTrackColor = Gray
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemVolumeSettingPreview() {
    val sliderValue = remember { mutableStateOf(0.5f) }

    ItemVolumeSetting(
        imgResId = R.drawable.ic_peta,  // Replace with an actual drawable resource
        value = sliderValue.value,
        onValueChange = { newValue -> sliderValue.value = newValue }
    )
}