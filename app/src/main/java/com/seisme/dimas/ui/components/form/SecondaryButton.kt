package com.seisme.dimas.ui.components.form

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecondaryButton(
    text: String,
    textColor: Color,
    imageResId: Int,
    onClick: () -> Unit,
    borderColor: List<Color>
) {
    OutlinedButton(
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = borderColor
            )
        ),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = textColor,
            disabledContentColor = Color.DarkGray,
            disabledContainerColor = Color.Gray
        ),
        onClick = onClick,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}