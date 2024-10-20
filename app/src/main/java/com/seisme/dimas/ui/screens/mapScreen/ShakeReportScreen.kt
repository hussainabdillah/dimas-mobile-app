import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.R
import com.seisme.dimas.ui.theme.Orange

@Composable
fun ShakeReportScreen(onCloseClick: () -> Unit = {}) {
    var selectedIntensity by remember { mutableStateOf("Medium quake") }
    var comment by remember { mutableStateOf("") }
    var floor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        // Title Row with "Shaking Report" and Close button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Shaking Report",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 4.dp)
            )

            // Close icon button
            IconButton(onClick = { onCloseClick() }) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Black
                )
            }
        }

        // Earthquake intensity options using drawable resources
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ShakeIntensityOption(
                label = "Felt nothing",
                imgResId = R.drawable.ic_felt_nothing, // Custom drawable
                isSelected = selectedIntensity == "Felt nothing",
                onClick = { selectedIntensity = "Felt nothing" }
            )
            ShakeIntensityOption(
                label = "Slight tremor",
                imgResId = R.drawable.ic_slight_tremor,
                isSelected = selectedIntensity == "Slight tremor",
                onClick = { selectedIntensity = "Slight tremor" }
            )
            ShakeIntensityOption(
                label = "Medium quake",
                imgResId = R.drawable.ic_medium_quake,
                isSelected = selectedIntensity == "Medium quake",
                onClick = { selectedIntensity = "Medium quake" }
            )
            ShakeIntensityOption(
                label = "Strong quake",
                imgResId = R.drawable.ic_strong_quake,
                isSelected = selectedIntensity == "Strong quake",
                onClick = { selectedIntensity = "Strong quake" }
            )
            ShakeIntensityOption(
                label = "Very scary",
                imgResId = R.drawable.ic_very_scary,
                isSelected = selectedIntensity == "Very scary",
                onClick = { selectedIntensity = "Very scary" }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label =  { Text("Input comment (optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Floor input field
        OutlinedTextField(
            value = floor,
            onValueChange = { floor = it },
            label = { Text("Floor: (optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit button
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(2.dp, Orange, RoundedCornerShape(8.dp))
                .background(Color.White),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Orange
            )
        ) {
            Text(text = "Posting Report", fontSize = 20.sp)
        }
    }
}

@Composable
fun ShakeIntensityOption(
    label: String,
    imgResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color.Gray.copy(alpha = 0.3f) else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            val image: Painter = painterResource(id = imgResId)

            Icon(
                painter = image,
                contentDescription = label,
                tint = Color.Unspecified,
                modifier = Modifier.size(40.dp)
            )
        }

        Text(
            text = label,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShakeReportScreenPreview() {
    ShakeReportScreen()
}
