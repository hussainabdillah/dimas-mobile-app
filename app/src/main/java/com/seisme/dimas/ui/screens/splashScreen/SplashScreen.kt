package com.seisme.dimas.ui.screens.splashScreen

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seisme.dimas.ui.navigation.Routes
import kotlinx.coroutines.delay
import com.seisme.dimas.R

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val isLoggedIn = remember { mutableStateOf(sharedPreferences.getBoolean("isLoggedIn", false)) }
    var alpha by remember { mutableStateOf(0f) }
    val animatedAlpha = animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 1500)
    )

    LaunchedEffect(Unit) {
        alpha = 1f
        delay(1000) // Splash Screen tampil selama 2 detik
        navController.navigate(if (isLoggedIn.value) Routes.Map.route else Routes.Login.route) {
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = animatedAlpha.value },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.img_splash_screen), // Ganti `app_icon` dengan nama file ikon Anda
            contentDescription = null,
            modifier = Modifier.size(128.dp) // Atur ukuran ikon
        )
    }
}

//@Preview
//@Composable
//fun PreviewSplashScreen() {
//    SplashScreen(navController)
//}
