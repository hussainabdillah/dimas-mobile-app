package com.seisme.dimas.ui.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.ui.theme.White

@Composable
fun ItemProfileScreen(label: String, value: String) {
    Box (
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .background(White)
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                top = 8.dp,
                end = 16.dp,
                bottom = 8.dp,
                )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            LabelText(label = label)
            ValueText(value = value)
        }
    }
}

@Composable
fun LabelText(label: String) {
    Text(
        text = label,
        fontSize = 14.sp,
        color = Color.Black
    )
}

@Composable
fun ValueText(value: String) {
    Text(
        text = value,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}