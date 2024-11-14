package com.seisme.dimas.ui.screens.profileScreen

import android.content.Context
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.item.ItemProfileScreen
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.screens.profileScreen.logoutScreen.LogoutConfirmationDialog
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.PrimaryBackground
import com.seisme.dimas.ui.theme.White

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
    ) {

    var showLogoutDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    val userData by viewModel.userData.observeAsState()

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box {
                Column {
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
                                text = userData?.username ?: stringResource(R.string.antonio),
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
                                    contentDescription = stringResource(R.string.email_navigate),
                                    modifier = Modifier
                                        .size(20.dp),
                                    tint = Color.Black
                                )
                                Text(
                                    text = userData?.email ?: stringResource(R.string.antonio_email),
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
                        ItemProfileScreen(label = stringResource(R.string.mobile_number), value = userData?.contact ?: stringResource(R.string.antonio_number))
                        ItemProfileScreen(label = stringResource(R.string.username), value = userData?.username ?: stringResource(R.string.antonio_username))
                        ItemProfileScreen(label = stringResource(R.string.gender), value = userData?.gender ?: stringResource(R.string.antonio_gender))
                    }
                }
            }

            Box {
                Column {
                    Button(
                        colors = ButtonColors(
                            containerColor = LightBlue,
                            contentColor = White,
                            disabledContentColor = Color.Gray,
                            disabledContainerColor = Color.Gray
                        ),
                        onClick = {
                            navController.navigate(Routes.ListMember.route) {
                                popUpTo(Routes.ListMember.route)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.list_member),
                            fontSize = 14.sp,
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
                        onClick = {
                            showLogoutDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.logout),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }

            if (showLogoutDialog) {
                LogoutConfirmationDialog(
                    onDismiss = { showLogoutDialog = false },
                    onConfirmLogout = {
                        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
                        showLogoutDialog = false

                        navController.navigate(Routes.Login.route) {
                            popUpTo(Routes.Map.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
