package com.seisme.dimas.ui.screens.loginScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seisme.dimas.ui.components.CommonTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    // Observe changes in isLoggedIn to navigate to HomeScreen
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

        // Show error message if exists
        state.errorMessage?.let { errorMessage ->
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = { viewModel.loginWithEmailAndPassword() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.loginWithGoogle() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login with Google")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = { onNavigateToRegister() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Don't have an account? Register here")
        }
    }
}