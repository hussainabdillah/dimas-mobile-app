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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.ui.components.form.AuthTextField
import com.seisme.dimas.ui.components.form.PrimaryButton
import com.seisme.dimas.ui.components.form.SecondaryButton
import com.seisme.dimas.ui.theme.HeaderLightBlue
import com.seisme.dimas.ui.theme.White

@Preview(showBackground = true)
@Composable
fun AdditionalRegisterScreen() {
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
                text = "Set up your profile",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(48.dp))

        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Username",
            placeholder = "Enter your username",
            spacer = 16
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Contact",
            placeholder = "Enter your contact",
            spacer = 16
        )

        // Variable to handle dropdown
        var selectedGender by remember { mutableStateOf("") }
        val genderOptions = listOf("Male", "Female")

        AuthTextField(
            value = selectedGender,
            onValueChange = { selectedGender = it },
            label = "Gender",
            placeholder = "Select your gender",
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
                text = "Back",
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
                text = "Complete",
                textColor = White,
                containerColor = HeaderLightBlue,
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