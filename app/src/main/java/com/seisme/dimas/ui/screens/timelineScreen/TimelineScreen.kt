package com.seisme.dimas.ui.screens.timelineScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.theme.PrimaryBackground

@Composable
fun TimelineScreen(navController: NavHostController, viewModel: TimelineViewModel = hiltViewModel()) {
    val gempaData by viewModel.gempaData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getGempaData()
    }

    Scaffold(
        topBar = {
            Header(title = stringResource(R.string.timeline_header))
        },
    ) { padding ->
        Surface(
            color = PrimaryBackground,
        ) {
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .absoluteOffset(x = 130.dp, y = 0.dp)
                    .background(Color.LightGray)
            )
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
            ) {

                items(gempaData) { gempa -> // Jangan lupa 'items(gempaData)'
                    EarthquakeItem(
                        time = gempa.jam, // Sesuaikan dengan data JSON dari API BMKG
                        date = gempa.tanggal, // Sesuaikan dengan data JSON dari API BMKG
                        location = gempa.wilayah, // Sesuaikan dengan data JSON dari API BMKG
                        magnitude = gempa.magnitude, // Sesuaikan dengan data JSON dari API BMKG
                        onClick = {
                            navController.navigate("timelineDetail/${gempa.tanggal}/${gempa.wilayah}/${gempa.magnitude}/${gempa.coordinates}/${gempa.kedalaman}")
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun EarthquakeItem(time: String, date: String, location: String, magnitude: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(text = time, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = date, fontSize = 10.sp, fontWeight = FontWeight.Light)
        }
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
            shadowElevation = 3.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(text = location, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Row {
                        Text(
                            text = "Magnitudo",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                        Text(
                            text = magnitude,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Navigate",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TimelineScreenPreview() {
//    TimelineScreen(viewModel = DummyViewModel())
//}
