package com.seisme.dimas.ui.screens.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.item.ItemSetting
import com.seisme.dimas.ui.components.item.ItemVolumeSetting
import com.seisme.dimas.ui.components.item.SectionBox
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.PrimaryBackground

@Composable
fun SettingScreen(navController: NavHostController) {
    val volume = remember { mutableFloatStateOf(0.5f) }
    val isSilent = remember { mutableStateOf(false) }
    val isVibrationEnabled = remember { mutableStateOf(true) }
    val receiveEarthquakeInfo = remember { mutableStateOf(true) }
    val participateInDrill = remember { mutableStateOf(true) }
    val receiveTsunamiInfo = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Header(
                title = "Profile Settings",
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Routes.Profile.route) {
                        popUpTo(Routes.Profile.route)
                    }
                },
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
                    Text(text = "Volume", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemVolumeSetting(
                        imgResId = R.drawable.ic_volume,
                        value = volume.floatValue,
                        onValueChange = { volume.floatValue = it }
                    )

                    Text(text = "When in Manner Mode", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Silent",
                        imgResId = R.drawable.ic_silent,
                        isChecked = isSilent.value,
                        onCheckedChange = { isSilent.value = it }
                    )

                    Text(text = "Vibration", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Manner Mode Only",
                        imgResId = R.drawable.ic_vibration,
                        isChecked = isVibrationEnabled.value,
                        onCheckedChange = { isVibrationEnabled.value = it }
                    )
                }
            }

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
                        imgResId = R.drawable.ic_receive,
                        isChecked = receiveEarthquakeInfo.value,
                        onCheckedChange = { receiveEarthquakeInfo.value = it }
                    )

                    Text(text = "Earthquake Early Warning Drill", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Participate",
                        imgResId = R.drawable.ic_run,
                        isChecked = participateInDrill.value,
                        onCheckedChange = { participateInDrill.value = it }
                    )
                }
            }

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
                        imgResId = R.drawable.ic_receive,
                        isChecked = receiveTsunamiInfo.value,
                        onCheckedChange = { receiveTsunamiInfo.value = it }
                    )

                    Text(text = "Tsunami Early Warning Drill", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ItemSetting(
                        text = "Participate",
                        imgResId = R.drawable.ic_run,
                        isChecked = participateInDrill.value,
                        onCheckedChange = { participateInDrill.value = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}