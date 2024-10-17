package com.seisme.dimas.ui.screens.mitigasiScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seisme.dimas.R
import com.seisme.dimas.ui.components.item.ItemMitigation
import com.seisme.dimas.ui.components.navigation.Header
import com.seisme.dimas.ui.theme.PrimaryBackground

data class MitigationItem(
    val text: String,
    val imgResId: Int
)

@Composable
fun MitigasiScreen() {
    val mitigationItems = listOf(
        MitigationItem(
            imgResId = R.drawable.mitigation_item_1,
            text = "Di Dalam Ruangan"
        ),
        MitigationItem(
            imgResId = R.drawable.mitigation_item_2,
            text = "Di Luar Ruangan"
        ),
        MitigationItem(
            imgResId = R.drawable.mitigation_item_3,
            text = "Saat Berkendara"
        )
    )

    Scaffold(
        topBar = {
            Header(
                title = "Gempa Bumi",
                navigationIcon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                onNavigationClick = {},
                isIconAtStart = true
            )
        }
    ) { padding ->
        Surface(color = PrimaryBackground) {
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(
                        start = 20.dp,
                        top = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
            ) {
                items(mitigationItems.size) { index ->
                    ItemMitigation(
                        text = mitigationItems[index].text,
                        imgResId = mitigationItems[index].imgResId
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMitigasiScreen() {
    MitigasiScreen()
}