package com.seisme.dimas.ui.screens.loginScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    LaunchedEffect(state.isLoggedIn) {
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
                text = "Log In",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Enter into your account",
                color = Color.Gray,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(48.dp))

        AuthTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChanged,
            label = "Email",
            placeholder = "Enter your email",
            spacer = 16
        )
        AuthTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            label = "Password",
            placeholder = "Enter your password",
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
                text = "or",
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
            text = "Continue with Google",
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
            text = "Continue",
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
            Text(text = "Don't have an account? ")
            TextButton(
                onClick = { onNavigateToRegister() },
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = LightBlue,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                contentPadding = PaddingValues(0.dp),
                content = { Text(text = "Register") }
            )
        }
    }
}

