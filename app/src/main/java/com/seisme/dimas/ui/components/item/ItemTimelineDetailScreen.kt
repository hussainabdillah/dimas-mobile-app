package com.seisme.dimas.ui.components.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InformationDetail(
    tanggal: String,
    pusatGempa: String,
    magnitudo: String,
    intensitas: String,
    kedalaman: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(10.dp, RoundedCornerShape(16.dp))
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                DetailRow(label = "Tanggal", value = tanggal)
                DetailRow(label = "Pusat Gempa", value = pusatGempa)
                DetailRow(label = "Magnitudo", value = magnitudo)
                DetailRow(label = "Intensitas", value = intensitas)
                DetailRow(label = "Kedalaman", value = kedalaman)
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
    }
}
