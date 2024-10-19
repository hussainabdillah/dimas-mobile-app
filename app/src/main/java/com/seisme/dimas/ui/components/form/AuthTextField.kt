package com.seisme.dimas.ui.components.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.R
import com.seisme.dimas.ui.theme.GoogleGradientBlue
import com.seisme.dimas.ui.theme.GoogleGradientGreen
import com.seisme.dimas.ui.theme.GoogleGradientRed
import com.seisme.dimas.ui.theme.GoogleGradientYellow
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    spacer: Int,
    isPassword: Boolean = false,
    isDropdown: Boolean = false,
    options: List<String> = emptyList()
) {
    Text(text = label, fontSize = 16.sp)

    if (isDropdown) {
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = { },
                placeholder = { Text(text = placeholder, color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = MaterialTheme.shapes.small,
                readOnly = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                ),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    } else {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder, color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = MaterialTheme.shapes.small,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Gray,
                unfocusedBorderColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }

    Spacer(modifier = Modifier.height(spacer.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
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
            value = "",
            onValueChange = {},
            label = "Email",
            placeholder = "Enter your email",
            spacer = 16
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Password",
            placeholder = "Enter your password",
            spacer = 16
        )

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
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account? ")
            TextButton(
                onClick = {  },
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

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
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
                text = "Create Account",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(48.dp))

        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Email",
            placeholder = "Enter your email",
            spacer = 16
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Password",
            placeholder = "Enter your password",
            spacer = 16
        )
        AuthTextField(
            value = "",
            onValueChange = {},
            label = "Confirm Password",
            placeholder = "Confirm your password",
            spacer = 16
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
                text = "Next",
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

@Preview(showBackground = true)
@Composable
fun PreviewSecondRegisterScreen() {
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
                containerColor = LightBlue,
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

@Preview(showBackground = true)
@Composable
fun PreviewDropsown() {
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
}