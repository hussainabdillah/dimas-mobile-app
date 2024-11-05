package com.seisme.dimas.ui.screens.timelineScreen

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.theme.PrimaryBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TimelineScreen(
    navController: NavHostController,
    viewModel: TimelineViewModel = hiltViewModel()
) {
    val earthquakeData by viewModel.earthquakeData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchEarthquakeData()
    }

    Scaffold(
        topBar = {
            Header(title = stringResource(R.string.timeline_header))
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) { _ ->
        Surface(
            color = PrimaryBackground
        ) {
            when {
                isLoading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 55.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    Text(text = errorMessage!!, color = Color.Red)
                }

                earthquakeData.isEmpty() -> {
                    Text(text = stringResource(R.string.no_earthquake_data), color = Color.Black)
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .absoluteOffset(x = 150.dp, y = 0.dp)
                            .background(Color.LightGray)
                    )
                    LazyColumn(
                        modifier = Modifier.padding(
                            bottom = 80.dp,
                            top = 55.dp
                        )
                    ) {
                        items(earthquakeData) { earthquake ->
                            EarthquakeItem(
                                time = earthquake.time,
                                date = earthquake.date,
                                location = earthquake.location,
                                magnitude = earthquake.magnitude,
                                onClick = {
                                    navController.navigate("linimasaDetail/${earthquake.date}/${earthquake.time}/${earthquake.location}/${earthquake.magnitude}/${earthquake.depth}/${earthquake.coordinates}/")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EarthquakeItem(
    time: String,
    date: String,
    location: String,
    magnitude: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                start = 20.dp,
                top = 10.dp,
                end = 0.dp,
                bottom = 10.dp,
            )
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Text(
                text = time,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = date,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color.Black
            )
        }
        Surface(
            shadowElevation = (1.dp),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = 8.dp
            ),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = location,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.widthIn(max = 180.dp),
                        overflow = TextOverflow.Visible
                    )
                    Row {
                        Text(
                            text = stringResource(R.string.magnitude),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 6.dp),
                            color = Color.Black
                        )
                        Text(
                            text = magnitude,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.email_navigate),
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    tint = Color.Black
                )
            }
        }
    }
}