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
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.Header
import com.seisme.dimas.ui.components.InformationDetail

@Composable
fun TimelineDetailScreen() {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.header_detail_informasi),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClick = {  }
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            InformationDetail(
                tanggal = "14.41 WIB, 20 Sept 2019",
                pusatGempa = "Selatan Gunung Semeru",
                magnitudo = "2.2",
                intensitas = "3",
                kedalaman = "Sangat Dangkal"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimelineDetailScreenPreview() {
    TimelineDetailScreen()
}
