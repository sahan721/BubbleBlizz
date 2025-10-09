package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.ThemeViewModel
import com.example.bubbleblizz.ui.ThemeMode
import com.example.bubbleblizz.util.UserStore
import com.example.bubbleblizz.ui.component.BackTopBar

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onMyAccount: () -> Unit,
    onLogout: () -> Unit,
    themeVm: ThemeViewModel
) {
    val profile by UserStore.profile
    var darkChecked by remember { mutableStateOf(themeVm.mode.value == ThemeMode.DARK) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { BackTopBar(title = "Settings", onBack = onBack) }
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            val cardGradient = Brush.verticalGradient(
                listOf(Color(0xFF1E3CFF), Color(0xFF2A00A2))
            )

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(cardGradient)
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFFFB3C1))
                    )
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "${profile.firstName} ${profile.lastName}",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            profile.email,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Text(
                "Profile",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(8.dp))

            ProfileRow(
                icon = Icons.Outlined.Person,
                title = "My Account",
                subtitle = "Make changes to your account",
                trailing = { Icon(Icons.Outlined.ChevronRight, contentDescription = null) },
                onClick = onMyAccount
            )

            ProfileRow(
                icon = Icons.Outlined.Lock,
                title = "Face ID / Touch ID",
                subtitle = "Manage your device security",
                trailing = { Switch(checked = false, onCheckedChange = {}) }
            )

            ProfileRow(
                icon = Icons.Outlined.Nightlight,
                title = "Dark Mode",
                subtitle = "",
                trailing = {
                    Switch(
                        checked = darkChecked,
                        onCheckedChange = { checked ->
                            darkChecked = checked
                            themeVm.toggleDark(checked)
                        }
                    )
                }
            )

            Spacer(Modifier.height(12.dp))

            ProfileRow(
                icon = Icons.Outlined.Logout,
                title = "Log out",
                subtitle = "Further secure your account for safety",
                trailing = { Icon(Icons.Outlined.ChevronRight, contentDescription = null) },
                onClick = onLogout
            )

            Spacer(Modifier.height(16.dp))
            Text(
                "More",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            ProfileRow(
                icon = Icons.Outlined.Notifications,
                title = "Help & Support",
                subtitle = "",
                trailing = { Icon(Icons.Outlined.ChevronRight, contentDescription = null) }
            )

            ProfileRow(
                icon = Icons.Outlined.Info,
                title = "About App",
                subtitle = "",
                trailing = { Icon(Icons.Outlined.ChevronRight, contentDescription = null) }
            )

            Spacer(Modifier.height(24.dp)) // smooth bottom padding
        }
    }
}

@Composable
private fun ProfileRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    trailing: @Composable () -> Unit,
    onClick: (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(28.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            if (subtitle.isNotEmpty())
                Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        trailing()
    }
    Divider()
}
