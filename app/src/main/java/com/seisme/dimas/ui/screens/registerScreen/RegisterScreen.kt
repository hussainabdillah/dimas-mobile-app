package com.seisme.dimas.ui.screens.registerScreen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.form.AuthTextField
import com.seisme.dimas.ui.components.form.PrimaryButton
import com.seisme.dimas.ui.components.form.SecondaryButton
import com.seisme.dimas.ui.theme.White

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel? = hiltViewModel(),
    onNavigateToLogin: () -> Unit?
) {
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
            value = "",
            onValueChange = {},
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.input_email),
            spacer = 16
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.input_password),
            spacer = 16
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.input_confirm_password),
            spacer = 16
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
                onClick = { },
                borderColor = listOf(Color.LightGray, Color.LightGray),
                modifier = Modifier
                    .background(Color.Transparent),
            )

            PrimaryButton(
                text = stringResource(R.string.next),
                textColor = White,
                containerColor = Color.Black,
                onClick = {},
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