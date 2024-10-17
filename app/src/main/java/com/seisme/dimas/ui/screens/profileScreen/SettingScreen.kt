package com.seisme.dimas.ui.screens.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.item.ItemSetting
import com.seisme.dimas.ui.components.item.ItemVolumeSetting
import com.seisme.dimas.ui.components.item.SectionBox
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.theme.PrimaryBackground

@Composable
fun SettingsScreen() {
    val volume = remember { mutableStateOf(0.5f) }
    val isSilent = remember { mutableStateOf(false) }
    val isVibrationEnabled = remember { mutableStateOf(true) }
    val receiveEarthquakeInfo = remember { mutableStateOf(true) }
    val participateInDrill = remember { mutableStateOf(true) }
    val receiveTsunamiInfo = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Header(
                title = "Profile Settings",
                navigationIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onNavigationClick = { /* Handle back navigation */ },
                isIconAtStart = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(PrimaryBackground)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Volume & Vibration Section
            Text(
                text = "Volume & Vibration",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
            )
            SectionBox {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Use ItemVolumeSetting for the Volume slider
                    Text(text = "Volume", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemVolumeSetting(
                        imgResId = R.drawable.ic_peta,  // Replace with actual icon resource
                        value = volume.value,
                        onValueChange = { volume.value = it }
                    )

                    // Silent Mode
                    Text(text = "When in Manner Mode", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Silent",
                        imgResId = R.drawable.ic_peta,  // Replace with actual drawable resource
                        isChecked = isSilent.value,
                        onCheckedChange = { isSilent.value = it }
                    )

                    // Vibration Setting
                    Text(text = "Vibration", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Manner Mode Only",
                        imgResId = R.drawable.ic_peta,  // Replace with actual drawable resource
                        isChecked = isVibrationEnabled.value,
                        onCheckedChange = { isVibrationEnabled.value = it }
                    )
                }
            }

            // Earthquake Section
            Text(
                text = "Earthquake",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            SectionBox {
                Column {
                    Text(text = "Earthquake info", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Receive",
                        imgResId = R.drawable.ic_peta,  // Replace with actual drawable resource
                        isChecked = receiveEarthquakeInfo.value,
                        onCheckedChange = { receiveEarthquakeInfo.value = it }
                    )

                    Text(text = "Earthquake Early Warning Drill", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Participate",
                        imgResId = R.drawable.ic_peta,  // Replace with actual drawable resource
                        isChecked = participateInDrill.value,
                        onCheckedChange = { participateInDrill.value = it }
                    )
                }
            }

            // Tsunami Section
            Text(
                text = "Tsunami",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            SectionBox {
                Column {
                    Text(text = "Tsunami info", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Receive",
                        imgResId = R.drawable.ic_peta,  // Replace with actual drawable resource
                        isChecked = receiveTsunamiInfo.value,
                        onCheckedChange = { receiveTsunamiInfo.value = it }
                    )

                    Text(text = "Tsunami Early Warning Drill", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Participate",
                        imgResId = R.drawable.ic_peta,  // Replace with actual drawable resource
                        isChecked = participateInDrill.value,
                        onCheckedChange = { participateInDrill.value = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}