package com.seisme.dimas.ui.screens.mitigationScreen

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.PrimaryBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TsunamiMitigationScreen(navController: NavHostController) {
    val dropdownVisibility = remember { mutableIntStateOf(-1) }

    Scaffold(
        topBar = {
            Header(
                title = stringResource(id = R.string.tsunami_header),
                navigationIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onNavigationClick = {
                    navController.navigate(Routes.Mitigation.route) {
                        popUpTo(Routes.Mitigation.route)
                    }
                },
                isIconAtStart = true
            )
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) { _ ->
        Surface(
            color = PrimaryBackground,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 90.dp, top = 65.dp)
                    .verticalScroll(rememberScrollState())
            ) {

            }
        }
    }
}