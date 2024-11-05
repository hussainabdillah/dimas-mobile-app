package com.seisme.dimas.ui.screens.mapScreen

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.seisme.dimas.R
import com.seisme.dimas.ui.theme.Orange
import com.seisme.dimas.ui.theme.White


@Composable
fun ShakeReportScreen(
    viewModel: ShakeReportViewModel = hiltViewModel(),
    onCloseClick: () -> Unit = {},
    user: String = stringResource(R.string.default_user),
) {
    val intensity = viewModel.intensity
    val comment = viewModel.comment
    val floor = viewModel.floor
    val isLoading = viewModel.isLoading
    val isSuccess = viewModel.isSuccess
    val errorMessage = viewModel.errorMessage

    if (isSuccess) {
        Text(text = stringResource(R.string.report_success))
    }

    val context = LocalContext.current
    val permissionState = remember { mutableStateOf(false) }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_PERMISSION
        )
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionState.value = true
        } else {
            requestLocationPermission()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .background(White)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.shaking_report),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 4.dp),
                    color = Color.Black
                )

                // Close icon button
                IconButton(onClick = { onCloseClick() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close),
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
                    label = stringResource(R.string.felt_nothing),
                    selectedImgResId = R.drawable.ic_felt_nothing,
                    unselectImgResId = R.drawable.ic_not_felt_nothing,
                    isSelected = intensity == 1,
                    onClick = { viewModel.intensity = 1 }
                )
                ShakeIntensityOption(
                    label = stringResource(R.string.slight_tremor),
                    selectedImgResId = R.drawable.ic_slight_tremor,
                    unselectImgResId = R.drawable.ic_not_slight_tremor,
                    isSelected = intensity == 2,
                    onClick = { viewModel.intensity = 2 }
                )
                ShakeIntensityOption(
                    label = stringResource(R.string.medium_quake),
                    selectedImgResId = R.drawable.ic_medium_quake,
                    unselectImgResId = R.drawable.ic_not_medium_quake,
                    isSelected = intensity == 3,
                    onClick = { viewModel.intensity = 3 }
                )
                ShakeIntensityOption(
                    label = stringResource(R.string.strong_quake),
                    selectedImgResId = R.drawable.ic_strong_quake,
                    unselectImgResId = R.drawable.ic_not_strong_quake,
                    isSelected = intensity == 4,
                    onClick = { viewModel.intensity = 4 }
                )
                ShakeIntensityOption(
                    label = stringResource(R.string.very_scary),
                    selectedImgResId = R.drawable.ic_very_scary,
                    unselectImgResId = R.drawable.ic_not_very_scary,
                    isSelected = intensity == 5,
                    onClick = { viewModel.intensity = 5 }
                )
            }

            TextField(
                value = comment,
                onValueChange = { viewModel.comment = it },
                placeholder = { Text(stringResource(R.string.input_comment)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.LightGray,
                    focusedPlaceholderColor = Color.Black,
                    unfocusedPlaceholderColor = Color.LightGray,
                    cursorColor = Color.Black
                ),
            )

            TextField(
                value = floor,
                onValueChange = { viewModel.floor = it },
                label = { Text(stringResource(R.string.floor)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.LightGray,
                    focusedPlaceholderColor = Color.Black,
                    unfocusedPlaceholderColor = Color.LightGray,
                    cursorColor = Color.Black
                ),
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Submit button
            Button(
                onClick = { viewModel.getLocationAndSubmitReport(user) },
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
                Text(text = if (isLoading) stringResource(R.string.submit) else stringResource(R.string.posting_report), fontSize = 20.sp)
            }

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            }
        }
    }
}

@Composable
fun ShakeIntensityOption(
    label: String,
    selectedImgResId: Int,
    unselectImgResId: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            val image: Painter = painterResource(id = if (isSelected) selectedImgResId else unselectImgResId)

            Icon(
                painter = image,
                contentDescription = label,
                tint = Color.Unspecified,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier.widthIn(max = 50.dp),
            textAlign = TextAlign.Center,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            lineHeight = 16.sp
        )
    }
}

const val REQUEST_LOCATION_PERMISSION = 1001