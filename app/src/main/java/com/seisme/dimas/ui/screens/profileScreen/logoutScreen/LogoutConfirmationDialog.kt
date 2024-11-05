package com.seisme.dimas.ui.screens.profileScreen.logoutScreen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.R
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
            text = stringResource(R.string.logout_confirm),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.widthIn(max = 200.dp),
            overflow = TextOverflow.Visible
        ) },
        text = { Text(
            text = stringResource(R.string.logout_confirmation),
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
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
                        text = stringResource(R.string.logout),
                        color = LightBlue
                    )
                }
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                            .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
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