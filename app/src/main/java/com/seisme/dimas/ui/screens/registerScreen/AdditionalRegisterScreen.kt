package com.seisme.dimas.ui.screens.registerScreen

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.form.AuthTextField
import com.seisme.dimas.ui.components.form.PrimaryButton
import com.seisme.dimas.ui.components.form.SecondaryButton
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.White


@Composable
fun AdditionalRegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val email = navController.currentBackStackEntry?.arguments?.getString("email") ?: ""
    val password = navController.currentBackStackEntry?.arguments?.getString("password") ?: ""

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
                text = stringResource(R.string.setup_profile),
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(48.dp))

        AuthTextField(
            value = state.username,
            onValueChange = viewModel::onUsernameChanged,
            label = stringResource(R.string.username),
            placeholder = stringResource(R.string.input_username),
            spacer = 16
        )
        AuthTextField(
            value = state.contact,
            onValueChange = viewModel::onContactChanged,
            label = stringResource(R.string.contact),
            placeholder = stringResource(R.string.input_contact),
            spacer = 16
        )

        // Variable to handle dropdown
        // masih bug disini dropdown tidak mau muncul
        var selectedGender by remember { mutableStateOf("Male") }
        val genderOptions = listOf("Male", "Female")

        AuthTextField(
            value = selectedGender,
            onValueChange = { selectedGender = it },
            label = stringResource(R.string.gender),
            placeholder = stringResource(R.string.select_gender),
            spacer = 16,
            isDropdown = true,
            options = genderOptions
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
                    navController.navigate(Routes.Register.route) {
                        popUpTo(Routes.Register.route)
                    }
                },
                borderColor = listOf(Color.LightGray, Color.LightGray),
                modifier = Modifier
                    .background(Color.Transparent),
            )

            PrimaryButton(
                text = stringResource(R.string.complete),
                textColor = White,
                containerColor = LightBlue,
                onClick = {
                    if (
                        state.username.isNotBlank() &&
                        state.contact.isNotBlank()
                        ){
                            viewModel.completeRegistration(
                                email = email,
                                password = password,
                                username = state.username,
                                contact = state.contact,
                                gender = selectedGender,
                                onSuccess = {
                                    onNavigateToLogin()
                                    Toast.makeText(
                                        context,
                                        "Registration Success",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onError = {
                                    Toast.makeText(
                                        context,
                                        "Registration Failed, Please Try Again",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )

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