package com.seisme.dimas.ui.screens.registerScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.form.AuthTextField
import com.seisme.dimas.ui.components.form.PrimaryButton
import com.seisme.dimas.ui.components.form.SecondaryButton
import com.seisme.dimas.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    navController: NavHostController
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

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
                text = stringResource(R.string.create_account),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
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
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.input_password),
            spacer = 16,
            isPassword = true
        )
        AuthTextField(
            value = state.confirmationPassword,
            onValueChange = viewModel::onConfirmationPasswordChanged,
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.input_confirm_password),
            spacer = 16,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SecondaryButton(
                text = stringResource(R.string.back),
                textColor = Color.LightGray,
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = {
                    onNavigateToLogin()
                },
                borderColor = listOf(Color.LightGray, Color.LightGray),
                modifier = Modifier
                    .background(Color.Transparent),
            )

            PrimaryButton(
                text = stringResource(R.string.next),
                textColor = White,
                containerColor = Color.Black,
                onClick = {
                    if (state.email.isNotBlank() &&
                        state.password.isNotBlank() &&
                        state.confirmationPassword.isNotBlank()
                    ) {
                        if (state.password == state.confirmationPassword) {
                            navController.navigate("additional_register?email=${state.email}&password=${state.password}")
                        } else {
                            Toast.makeText(
                                context,
                                "Password and confirmation password do not match",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill in all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                },
                iconOnRight = true,
                modifier = Modifier
                    .background(Color.Transparent),
            )
        }
    }
}