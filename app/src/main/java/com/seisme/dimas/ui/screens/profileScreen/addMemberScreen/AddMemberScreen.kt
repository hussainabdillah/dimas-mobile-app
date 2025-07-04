package com.seisme.dimas.ui.screens.profileScreen.addMemberScreen

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.data.model.UserData
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.screens.profileScreen.logoutScreen.LogoutConfirmationDialog
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.PrimaryBackground

@Composable
fun AddMemberScreen(
    navController: NavHostController,
    viewModel: MemberViewModel = hiltViewModel()
) {
    var showAddMemberDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val users by viewModel.users.observeAsState(emptyList())
    var selectedUser by remember { mutableStateOf<UserData?>(null) }
    var loading by remember { mutableStateOf(false) }

    // fetch data user terbaru
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.add_member),
                navigationIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onNavigationClick = {
                    navController.navigate(Routes.ListMember.route) {
                        popUpTo(Routes.ListMember.route)
                    }
                },
                isIconAtStart = true
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
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search),
                        color = Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.add_member),
                        color = LightBlue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(
                                top = 20.dp,
                                start = 20.dp,
                                bottom = 20.dp
                            )
                    )
                    LazyColumn {
                        items(users.filter {
                            it.username.contains(searchQuery, ignoreCase = true)
                        }) { item ->
                            AddMemberItem(
                                name = item.username,
                                onAddClick = {
                                    selectedUser = item
                                    showAddMemberDialog = true
                                }
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .background(PrimaryBackground)
                            )
                        }
                    }
                }
            }
        }
        if (showAddMemberDialog) {
            AddMemberConfirmationDialog(
                onDismiss = { showAddMemberDialog = false },
                onConfirmAddMember = {
                    // handle add member
                    loading = true
                    selectedUser?.let { user ->
                        viewModel.addMemberBothUsers(user)
                    }
                    showAddMemberDialog = false
                    navController.navigate(Routes.Profile.route) {
                        popUpTo(Routes.Profile.route) { inclusive = true }
                    }
                }
            )
        }

        if (loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun AddMemberItem(
    name: String,
    onAddClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Box {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
        IconButton(
            onClick = onAddClick
        ) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}