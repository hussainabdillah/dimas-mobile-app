package com.seisme.dimas.ui.components.BottomNavigation

import androidx.compose.foundation.border
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.seisme.dimas.R

@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.DarkGray,
        modifier = Modifier.border(1.dp, Color.LightGray)
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.LocationOn, contentDescription = stringResource(R.string.navigation_map)) },
            selected = selectedItem == BottomNavItem.Map,
            onClick = { onItemSelected(BottomNavItem.Map) },
            label = { Text(stringResource(R.string.navigation_map)) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = stringResource(R.string.navigation_timeline)) },
            selected = selectedItem == BottomNavItem.Timeline,
            onClick = { onItemSelected(BottomNavItem.Timeline) },
            label = { Text(stringResource(R.string.navigation_timeline)) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Warning, contentDescription = stringResource(R.string.navigation_mitigation)) },
            selected = selectedItem == BottomNavItem.Mitigation,
            onClick = { onItemSelected(BottomNavItem.Mitigation) },
            label = { Text(stringResource(R.string.navigation_mitigation)) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = stringResource(R.string.navigation_profile)) },
            selected = selectedItem == BottomNavItem.Profile,
            onClick = { onItemSelected(BottomNavItem.Profile) },
            label = { Text(stringResource(R.string.navigation_profile)) }
        )
    }
}
