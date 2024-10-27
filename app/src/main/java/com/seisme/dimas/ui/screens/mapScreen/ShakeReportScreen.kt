package com.seisme.dimas.ui.screens.mapScreen

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.seisme.dimas.R
import com.seisme.dimas.ui.screens.mapScreen.ShakeReportViewModel
import com.seisme.dimas.ui.theme.Orange


@Composable
fun ShakeReportScreen(
    viewModel: ShakeReportViewModel = hiltViewModel(),
    onCloseClick: () -> Unit = {},
    user: String = "defaultUser"
) {
    val intensity = viewModel.intensity
    val comment = viewModel.comment
    val floor = viewModel.floor
    val isLoading = viewModel.isLoading
    val isSuccess = viewModel.isSuccess
    val errorMessage = viewModel.errorMessage

    if (isSuccess) {
        // Show success message or navigate away
        Text(text = "Report submitted successfully!")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
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

        // Earthquake intensity options
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ShakeIntensityOption(
                label = "Felt nothing",
                imgResId = R.drawable.ic_felt_nothing,
                isSelected = intensity == 1,
                onClick = { viewModel.intensity = 1 }
            )
            ShakeIntensityOption(
                label = "Slight tremor",
                imgResId = R.drawable.ic_slight_tremor,
                isSelected = intensity == 2,
                onClick = { viewModel.intensity = 2 }
            )
            ShakeIntensityOption(
                label = "Medium quake",
                imgResId = R.drawable.ic_medium_quake,
                isSelected = intensity == 3,
                onClick = { viewModel.intensity = 3 }
            )
            ShakeIntensityOption(
                label = "Strong quake",
                imgResId = R.drawable.ic_strong_quake,
                isSelected = intensity == 4,
                onClick = { viewModel.intensity = 4 }
            )
            ShakeIntensityOption(
                label = "Very scary",
                imgResId = R.drawable.ic_very_scary,
                isSelected = intensity == 5,
                onClick = { viewModel.intensity = 5 }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = comment,
            onValueChange = { viewModel.comment = it },
            label = { Text("Input comment (optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Floor input field
        OutlinedTextField(
            value = floor,
            onValueChange = { viewModel.floor = it },
            label = { Text("Floor: (optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit button
        Button(
            onClick = { viewModel.submitReport(user) },
            enabled = !isLoading,
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
            Text(text = if (isLoading) "Submitting..." else "Posting Report", fontSize = 20.sp)
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
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
