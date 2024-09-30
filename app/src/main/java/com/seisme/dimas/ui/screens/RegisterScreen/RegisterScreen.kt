package com.seisme.dimas.ui.screens.RegisterScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seisme.dimas.ui.components.CommonTextField

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = hiltViewModel(), onNavigateToLogin: () -> Unit) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        CommonTextField(
            value = state.email,
            onValueChange = viewModel::onEmailChanged,
            label = "Email"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonTextField(
            value = state.password,
            onValueChange = viewModel::onPasswordChanged,
            label = "Password",
            isPassword = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.registerWithEmailAndPassword() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.loginWithGoogle() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register with Google")
        }
    }
}