package com.seisme.dimas.ui.screens.timelineScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.components.item.InformationDetail
import com.seisme.dimas.ui.navigation.Routes

@Composable
fun TimelineDetailScreen(
    tanggal: String,
    wilayah: String,
    magnitudo: String,
    coordinates: String,
    kedalaman: String,
    navController: NavController
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.header_detail_informasi),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Routes.Timeline.route) {
                        popUpTo(Routes.Timeline.route)
                    }
                },
                isIconAtStart = true
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            InformationDetail(
                tanggal = tanggal,
                wilayah = wilayah,
                magnitudo = magnitudo,
                coordinates = coordinates,
                kedalaman = kedalaman
            )
        }
    }
}
