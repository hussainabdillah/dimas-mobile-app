package com.seisme.dimas.ui.components.item.mitigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.data.repository.MitigationRepository
import com.seisme.dimas.ui.theme.LightBlue
import com.seisme.dimas.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemMitigationDropdown(
    details: List<MitigationRepository.MitigationDetail>,
    onItemClick: (MitigationRepository.MitigationDetail) -> Unit
) {
    var selectedItem by remember { mutableStateOf<MitigationRepository.MitigationDetail?>(null) }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        details.forEach { detail ->
            val isSelected = detail == selectedItem
            FlexItem(
                text = detail.detailTitle,
                imgResId = detail.detailImg,
                isSelected = isSelected,
                onClick = {
                    selectedItem = if (isSelected) null else detail
                    onItemClick(detail)
                }
            )
        }
    }
}

@Composable
fun FlexItem(
    text: String,
    imgResId: Int,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(0.2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .background(
                color = if (isSelected) LightBlue else White,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Button(
            onClick = {},
            colors = ButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContentColor = Color.LightGray,
                disabledContainerColor = Color.LightGray
            ),
            contentPadding = PaddingValues(10.dp)
        ) {
            Image(
                painter = painterResource(imgResId),
                contentDescription = null,
                modifier = Modifier
                    .width(75.dp)
                    .padding(end = 10.dp)
            )
            Text(
                text = text,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 12.sp
            )
        }
    }
}