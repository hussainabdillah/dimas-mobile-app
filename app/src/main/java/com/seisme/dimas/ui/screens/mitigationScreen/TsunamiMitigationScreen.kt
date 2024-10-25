package com.seisme.dimas.ui.screens.mitigationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.item.ItemMitigation
import com.seisme.dimas.ui.components.navigation.BottomNavigationBar
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.theme.PrimaryBackground

data class MitigationItems(
    val text: String,
    val imgResId: Int
)

@Composable
fun TsunamiMitigationScreen(navController: NavHostController) {
    val mitigationItems = listOf(
        MitigationItems(
            imgResId = R.drawable.mitigation_item_4,
            text = "Potensi Tsunami"
        ),
        MitigationItems(
            imgResId = R.drawable.mitigation_item_3,
            text = "Saat Berkendara"
        )
    )

    Scaffold(
        topBar = {
            Header(
                title = stringResource(id = R.string.tsunami_header),
                onNavigationClick = {},
            )
        },
        bottomBar = {
            BottomNavigationBar(navigationController = navController)
        }
    ) {padding ->
        Surface(color = PrimaryBackground) {
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(20.dp)
            ) {
                items(mitigationItems) { item ->
                    ItemMitigation(
                        text = item.text,
                        imgResId = item.imgResId
                    )
                }
            }
        }
    }
}