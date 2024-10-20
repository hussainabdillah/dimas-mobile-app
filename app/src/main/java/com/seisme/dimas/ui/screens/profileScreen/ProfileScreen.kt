package com.seisme.dimas.ui.screens.profileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.item.ItemProfileScreen
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.PrimaryBackground
import com.seisme.dimas.ui.theme.White

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.profile_header),
                navigationIcon = Icons.Filled.Settings,
                onNavigationClick = {
                    navController.navigate(Routes.Setting.route) {
                        popUpTo(Routes.Setting.route)
                    }
                },
                isIconAtStart = false
            )
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .background(PrimaryBackground)
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_profile),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = White
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Antonio Yono",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Filled.MailOutline,
                            contentDescription = "Navigate",
                            modifier = Modifier
                                .size(20.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = "YonoTonioo@gmail.com",
                            fontWeight = FontWeight.Light,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ItemProfileScreen(label = "Mobile Number", value = "+6289 1245 8131")
                ItemProfileScreen(label = "Username", value = "AntonYono")
                ItemProfileScreen(label = "Gender", value = "Laki-laki")
            }
            Spacer(modifier = Modifier.height(96.dp))
            Button(
                colors = ButtonColors(
                    containerColor = LightBlue,
                    contentColor = White,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                onClick = { /* Handle add member */ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Add Member",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = LightBlue,
                    disabledContentColor = Color.Gray,
                    disabledContainerColor = Color.Gray
                ),
                border = BorderStroke(1.dp, LightBlue),
                onClick = { /* Handle add member */ },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Log out",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}
