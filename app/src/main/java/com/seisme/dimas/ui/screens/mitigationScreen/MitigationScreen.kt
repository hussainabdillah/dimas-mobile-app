package com.seisme.dimas.ui.screens.mitigationScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.LightOrange
import com.seisme.dimas.ui.theme.PrimaryBackground

@Composable
fun MitigationScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Header(title = stringResource(R.string.earthquake_header))
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .background(PrimaryBackground)
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    RecommendationsSection(navController)
                }
                item {
                    AdditionalFeaturesSection()
                }
            }
        }
    }
}

@Composable
fun RecommendationsSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Recommendations for Action",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        // Earthquake Item
        RecommendationItem(
            imageRes = R.drawable.img_earthquake,
            label = stringResource(R.string.earthquake_header),
            backgroundColor = LightOrange,
            navController = navController,
            route = Routes.EarthquakeMitigation.route
        )

        // Tsunami Item
        RecommendationItem(
            imageRes = R.drawable.img_tsunami,
            label = stringResource(R.string.tsunami_header),
            backgroundColor = LightBlue,
            navController = navController,
            route = Routes.TsunamiMitigation.route
        )
    }
}

@Composable
fun RecommendationItem(
    imageRes: Int,
    label: String,
    backgroundColor: Color,
    navController: NavController,
    route: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .clickable {
                navController.navigate(route) {
                    popUpTo(route) { inclusive = true }
                }
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = label,
                modifier = Modifier.size(175.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .background(backgroundColor)
                    .padding(horizontal = 50.dp, vertical = 10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}


@Composable
fun AdditionalFeaturesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "Additional Features",
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FeatureButton(
                iconRes = R.drawable.ic_evacuation,
                label = stringResource(R.string.feature_evacuation),
                modifier = Modifier.weight(1f)

            )
            FeatureButton(
                iconRes = R.drawable.ic_supply,
                label = stringResource(R.string.feature_supply),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun FeatureButton(iconRes: Int, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { /* Handle Click Action */ }
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(53.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}