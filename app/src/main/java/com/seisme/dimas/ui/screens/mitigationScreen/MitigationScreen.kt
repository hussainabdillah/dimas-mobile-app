package com.seisme.dimas.ui.screens.mitigationScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.LightOrange
import com.seisme.dimas.ui.theme.PrimaryBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MitigationScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Header(title = stringResource(R.string.earthquake_header))
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) { _ ->
        Surface(color = PrimaryBackground) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp, top = 55.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Recommendations for Action",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )

                Box(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        RecommendationItem(
                            imageRes = R.drawable.img_earthquake,
                            label = stringResource(R.string.earthquake_header),
                            backgroundColor = LightOrange,
                            onClick = {
                                navController.navigate(Routes.EarthquakeMitigation.route) {
                                    popUpTo(Routes.EarthquakeMitigation.route)
                                }
                            }
                        )

                        Box(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(Color.LightGray)
                        )

                        RecommendationItem(
                            imageRes = R.drawable.img_tsunami,
                            label = stringResource(R.string.tsunami_header),
                            backgroundColor = LightBlue,
                            onClick = {
                                navController.navigate(Routes.TsunamiMitigation.route) {
                                    popUpTo(Routes.TsunamiMitigation.route)
                                }
                            }
                        )
                    }
                }

                Text(
                    text = "Additional Features",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                )

                Box(
                    modifier = Modifier.padding(
                        top = 0.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FeatureButton(
                            iconRes = R.drawable.ic_evacuation,
                            label = stringResource(R.string.feature_evacuation),
                            modifier = Modifier.weight(1f)
                        )

                        Box(
                            modifier = Modifier
                                .width(1.dp)
                                .height(75.dp)
                                .background(Color.LightGray)
                        )

                        FeatureButton(
                            iconRes = R.drawable.ic_supply,
                            label = stringResource(R.string.feature_supply),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun RecommendationItem(
    imageRes: Int,
    label: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = label,
            modifier = Modifier.size(175.dp),
            contentScale = ContentScale.Fit

        )
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .background(backgroundColor)
                .padding(horizontal = 50.dp, vertical = 4.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun FeatureButton(
    iconRes: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { /* Handle Click Action */ }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(50.dp),
            tint = Color.LightGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
    }
}