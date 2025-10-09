package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    TextButton(onClick = onCart) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingCart,
                                contentDescription = "Cart",
                                tint = Color.Black
                            )
                            Spacer(Modifier.width(4.dp))
                            Text("Cart", color = Color.Black)
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(12.dp)
        ) {
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
                                                    CartLine(
                                                        id = p.id,
                                                        name = p.name,
                                                        sub = p.size,
                                                        price = p.price,
                                                        qty = 1
                                                    )
                                                )
                                            }
                                        ) {
                                            Text("Add")
                                        }

                                        Spacer(Modifier.width(8.dp))
                                        FilledIconButton(
                                            onClick = {
                                                FavoritesStore.toggle(
                                                    FavLine(
                                                        id = p.id,
                                                        name = p.name,
                                                        size = p.size,
                                                        price = p.price,
                                                        rating = p.rating,
                                                        drawableName = p.drawableName
                                                    )
                                                )
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (isFav) Icons.Outlined.Star else Icons.Outlined.StarBorder,
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
