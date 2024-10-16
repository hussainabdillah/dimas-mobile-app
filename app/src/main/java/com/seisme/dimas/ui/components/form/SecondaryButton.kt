package com.seisme.dimas.ui.components.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.BorderStroke

@Composable
fun SecondaryButton(
    text: String,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier,
    borderColor: List<Color>? = null,
    icon: @Composable (() -> Unit)? = null,
    iconOnRight: Boolean = false
) {
    OutlinedButton(
        border = if (borderColor != null) {
            BorderStroke(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = borderColor
                )
            )
        } else {
            BorderStroke(1.dp, Color.Transparent)
        },
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = textColor,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.Gray
        ),
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (!iconOnRight && icon != null) {
                icon()
                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                text = text,
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            if (iconOnRight && icon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                icon()
            }
        }
    }
}