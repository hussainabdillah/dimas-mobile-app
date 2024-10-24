package com.seisme.dimas.ui.screens.logoutScreen

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.ui.theme.Gray
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.White

@Composable
fun LogoutConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirmLogout: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(
            text = "Konfirmasi Logout",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.widthIn(max = 200.dp),
            overflow = TextOverflow.Visible
        ) },
        text = { Text(
            text = "Ini akan mengakhiri sesi dan anda harus melakukan login kembali",
            color = Color.Black,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            modifier = Modifier.widthIn(max = 180.dp),
            overflow = TextOverflow.Visible
        ) },
        buttons = {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.widthIn(max = 230.dp),
            ) {
                TextButton(
                    onClick = onConfirmLogout,
                    modifier = Modifier
                        .border(1.dp, color = Color.LightGray)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Logout",
                        color = LightBlue
                    )
                }
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                            .fillMaxWidth()
                ) {
                    Text(
                        text = "Batal",
                        color = Gray
                    )
                }
            }
        },
        backgroundColor = White,
        contentColor = Color.Black,
        shape = RoundedCornerShape(16.dp)
    )
}