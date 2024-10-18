import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seisme.dimas.ui.theme.HeaderDarkBlue
import com.seisme.dimas.ui.theme.HeaderLightBlue

@Composable
fun HeaderSetting(
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    onProfileClick: (() -> Unit)? = null,
    isIconAtStart: Boolean? = true
) {
    TopAppBar(
        contentPadding = PaddingValues(0.dp),
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        elevation = 20.dp,
    ) {
        Box(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            HeaderLightBlue,
                            HeaderDarkBlue
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            // Navigasi Icon (Kiri atau Kanan tergantung isIconAtStart)
            if (navigationIcon != null && onNavigationClick != null) {
                if (isIconAtStart == true) {
                    IconButton(
                        onClick = onNavigationClick,
                        modifier = Modifier.align(Alignment.CenterStart).padding(start = 16.dp)
                    ) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = onNavigationClick,
                        modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp)
                    ) {
                        Icon(
                            imageVector = navigationIcon,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            }

            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )

            // Profile Icon Button di kanan
            if (onProfileClick != null) {
                IconButton(
                    onClick = onProfileClick,
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun HeaderSettingPreview() {
    HeaderSetting(
        title = "Profile Settings",
        navigationIcon = Icons.Filled.ArrowBack,
        onNavigationClick = { /* Aksi Back */ },
        onProfileClick = { /* Aksi Klik Profil */ },
        isIconAtStart = true
    )
}