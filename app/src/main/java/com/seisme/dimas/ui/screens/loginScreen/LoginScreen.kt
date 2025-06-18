package com.seisme.dimas.ui.screens.loginScreen

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.form.AuthTextField
import com.seisme.dimas.ui.components.form.PrimaryButton
import com.seisme.dimas.ui.components.form.SecondaryButton
import com.seisme.dimas.ui.theme.GoogleGradientBlue
import com.seisme.dimas.ui.theme.GoogleGradientGreen
import com.seisme.dimas.ui.theme.GoogleGradientRed
import com.seisme.dimas.ui.theme.GoogleGradientYellow
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.White

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val permissionState = remember { mutableStateOf(false) }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_PERMISSION
        )
    }

    LaunchedEffect(state.isLoggedIn) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            permissionState.value = true
        } else {
            requestLocationPermission()
        }
        if (state.isLoggedIn) {
            onNavigateToHome()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.login),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.enter_account),
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(48.dp))

        AuthTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChanged,
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.input_email),
            spacer = 16
        )
        AuthTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            isPassword = true,
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.input_password),
            spacer = 16
        )

        state.errorMessage?.let { errorMessage ->
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .height(1.dp)
                    .width(130.dp)
            )
            Text(
                text = stringResource(R.string.or),
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color.Gray,
                fontSize = 16.sp
            )
            Box(
                modifier = Modifier
                    .background(Color.Gray)
                    .height(1.dp)
                    .width(130.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        SecondaryButton(
            text = stringResource(R.string.continue_google),
            textColor = Color.Black,
            icon = {
                Image(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            },
            onClick = { },
            borderColor = listOf(
                GoogleGradientRed,
                GoogleGradientYellow,
                GoogleGradientGreen,
                GoogleGradientBlue
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        )
        Spacer(modifier = Modifier.height(60.dp))

        PrimaryButton(
            text = stringResource(R.string.next),
            textColor = White,
            containerColor = LightBlue,
            onClick = {viewModel.loginWithEmailAndPassword()},
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.dont_have_account))
            TextButton(
                onClick = { onNavigateToRegister() },
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = LightBlue,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                contentPadding = PaddingValues(0.dp),
                content = { Text(text = stringResource(R.string.register)) }
            )
        }
    }
}
const val REQUEST_LOCATION_PERMISSION = 1001

