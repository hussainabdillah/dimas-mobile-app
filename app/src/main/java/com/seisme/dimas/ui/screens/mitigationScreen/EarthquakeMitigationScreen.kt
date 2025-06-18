package com.seisme.dimas.ui.screens.mitigationScreen

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.data.repository.MitigationRepository
import com.seisme.dimas.data.repository.earthquakeMitigationItem
import com.seisme.dimas.ui.components.item.mitigation.ItemMitigation
import com.seisme.dimas.ui.components.item.mitigation.ItemMitigationDropdown
import com.seisme.dimas.ui.components.item.mitigation.ItemMitigationPopUp
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.navigation.Routes
import com.seisme.dimas.ui.theme.PrimaryBackground

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EarthquakeMitigationScreen(navController: NavHostController) {
    var dropdownVisibility by remember { mutableStateOf<Int?>(null) }
    var popUpVisibility by remember { mutableStateOf(false) }
    var selectedDetail by remember { mutableStateOf<MitigationRepository.MitigationDetail?>(null) }

    Scaffold(
        topBar = {
            Header(
                title = stringResource(R.string.earthquake_header),
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 90.dp, top = 65.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                earthquakeMitigationItem.forEachIndexed { index, item ->
                    ItemMitigation(
                        imgResId = item.itemImage,
                        text = item.itemTitle,
                        onClick = {
                            dropdownVisibility = if (dropdownVisibility == index) null else index
                        },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    AnimatedVisibility(
                        visible = dropdownVisibility == index,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        ItemMitigationDropdown(
                            details = item.itemDetails,
                            onItemClick = { detail ->
                                selectedDetail = detail
                                popUpVisibility = true
                            }
                        )
                    }

                    AnimatedVisibility(
                        visible = popUpVisibility
                    ) {
                        ItemMitigationPopUp(
                            title = "Default Title",
                            imageRes = selectedDetail?.detailImg ?: R.drawable.img_luar_ruangan,
                            onCloseClick = {
                                popUpVisibility = false
                                selectedDetail = null
                            }
                        )
                    }
                }
            }
        }
    }
}