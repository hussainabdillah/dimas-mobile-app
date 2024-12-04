package com.seisme.dimas.ui.components.item.mitigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemMitigationPopUp(
    title: String,
//    description: Any,
    imageRes: Int,
    onCloseClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 90.dp, horizontal = 24.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .background(Color.LightGray)
            ) {
                Box {
                    IconButton(
                        onClick = { onCloseClick() },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Back",
                            tint = Color.Gray
                        )
                    }
                }
                Text(
                    text = "Information Detail",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 60.dp),
                )
            }

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Image(
                painter = painterResource(imageRes),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            )

            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Text(
                text = "Penjelasan",
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            ) {
//                description.forEach { annotatedInfo ->
//                    Row(
//                        verticalAlignment = Alignment.Top,
//                        modifier = Modifier.padding(vertical = 4.dp)
//                    ) {
//                        if (annotatedInfo.isBullet) {
//                            Text(
//                                text = "• ",
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Bold,
//                                color = Color.DarkGray
//                            )
//                        }
//
//                        Text(
//                            text = annotatedInfo.text,
//                            fontSize = 14.sp,
//                            fontWeight = if (annotatedInfo.isBold) FontWeight.Bold else FontWeight.Normal,
//                            color = Color.DarkGray,
//                            textAlign = TextAlign.Justify,
//                            modifier = Modifier
//                                .weight(1f)
//                        )
//                    }
//                }
//            }
        }
    }
}