package com.seisme.dimas.ui.components.form

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.ui.theme.HeaderLightBlue

@Composable
fun PrimaryButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonColors(
            containerColor = HeaderLightBlue,
            contentColor = textColor,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.Gray
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}