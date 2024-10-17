package com.seisme.dimas.ui.components.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.ui.theme.HeaderLightBlue
import com.seisme.dimas.ui.theme.White

@Composable
fun ItemSetting(
    text: String,
    imgResId: Int,  // Icon resource ID
    isChecked: Boolean,  // Switch state
    onCheckedChange: (Boolean) -> Unit  // Callback for when the switch is toggled
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(White)
            .padding(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icon on the left side
                Image(
                    painter = painterResource(id = imgResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                )
                // Text in the middle
                Text(
                    text = text,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            // Switch on the right side
            Switch(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = White,
                    uncheckedThumbColor = Color.Gray,
                    checkedTrackColor = HeaderLightBlue
                )
            )

        }
    }
}

// Custom composable for the section box
@Composable
fun SectionBox(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)) // Rounded corners
            .background(White) // Background color
            .padding(vertical = 16.dp, horizontal = 16.dp) // Adjusted padding inside the box
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun ItemSettingPreview() {
    ItemSetting(
        text = "Receive Notifications",
        imgResId = com.seisme.dimas.R.drawable.ic_peta,  // Example icon
        isChecked = true,  // Example value
        onCheckedChange = { /* Handle switch change */ }
    )
}