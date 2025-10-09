package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.assets.Images
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.util.CartStore
import com.example.bubbleblizz.util.CartLine
import com.example.bubbleblizz.util.FavoritesStore
import com.example.bubbleblizz.util.FavLine
import com.example.bubbleblizz.util.Catalog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    categoryName: String,
    onProduct: (String) -> Unit,
    onCart: () -> Unit,
    onDetails: (String) -> Unit,
    onBack: () -> Unit
) {
    val items = remember { Catalog.getByCategory(categoryName) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            BackTopBar(
                title = categoryName,
                onBack = onBack,
                trailing = {
                    TextButton(onClick = onCart) { Text("Cart") }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(12.dp)) {
            if (items.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No items available in $categoryName")
                }
            } else {
                LazyColumn {
                    items(items) { p ->
                        val isFav = FavoritesStore.contains(p.id)
                        Card(
                            Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable { onDetails(p.id) }
                        ) {
                            Row(Modifier.padding(12.dp)) {
                                Image(
                                    painter = painterResource(Images.ofName(p.drawableName)),
                                    contentDescription = p.name,
                                    modifier = Modifier.size(56.dp)
                                )
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(p.name, style = MaterialTheme.typography.titleMedium)
                                    Text(p.size)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("LKR ${p.price}")
                                    Spacer(Modifier.height(4.dp))
                                    Row {
                                        OutlinedButton(
                                            onClick = {
                                                CartStore.add(
                                                    CartLine(p.id, p.name, p.size, p.price, 1)
                                                )
                                            }
                                        ) { Text("Add") }

                                        Spacer(Modifier.width(8.dp))
                                        FilledIconButton(
                                            onClick = {
                                                FavoritesStore.toggle(
                                                    FavLine(
                                                        p.id,
                                                        p.name,
                                                        p.size,
                                                        p.price,
                                                        p.rating
                                                    )
                                                )
                                            }
                                        ) {
                                            Icon(
                                                if (isFav) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                                                contentDescription = "Fav"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
