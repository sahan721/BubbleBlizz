package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.assets.Images
import com.example.bubbleblizz.ui.component.BubbleBottomBar
import com.example.bubbleblizz.ui.component.GradientHeader
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.util.FavLine
import com.example.bubbleblizz.util.FavoritesStore

@Composable
fun FavoritesScreen(
    onBack: () -> Unit,
    onGoHome: () -> Unit,
    onGoCart: () -> Unit,
    onGoSettings: () -> Unit
) {
    var search by remember { mutableStateOf("") }
    var tab by remember { mutableStateOf(2) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BubbleBottomBar(
                selected = tab,
                onSelect = {
                    tab = it
                    when (it) {
                        0 -> onGoHome()
                        1 -> onGoCart()
                        2 -> Unit
                        3 -> onGoSettings()
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            GradientHeader(search = search, onSearch = { search = it })
            BackTopBar(title = "Favorites", onBack = onBack)
            Divider()

            Column(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                for (fav in FavoritesStore.items) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = Images.ofName(fav.drawableName)),
                            contentDescription = fav.name,
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(Modifier.weight(1f)) {
                            Text(fav.name, style = MaterialTheme.typography.titleMedium)
                            Text(fav.size)
                            Text("LKR ${fav.price}")
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(String.format("%.1f", fav.rating))
                            Spacer(Modifier.width(4.dp))
                            Icon(
                                Icons.Outlined.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFC107)
                            )
                        }
                    }

                    Row(Modifier.fillMaxWidth()) {
                        Spacer(Modifier.weight(1f))
                        OutlinedButton(onClick = { FavoritesStore.remove(fav.id) }) {
                            Text("Remove")
                        }
                    }
                    Divider(Modifier.padding(vertical = 8.dp))
                }

                if (FavoritesStore.items.isEmpty()) {
                    Text("No favorites yet", modifier = Modifier.padding(top = 16.dp))
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
