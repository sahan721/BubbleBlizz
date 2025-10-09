package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.ui.assets.Images
import com.example.bubbleblizz.ui.component.BubbleBottomBar
import com.example.bubbleblizz.ui.component.GradientHeader
import com.example.bubbleblizz.ui.component.BackTopBar
import com.example.bubbleblizz.util.CartLine
import com.example.bubbleblizz.util.CartStore
import com.example.bubbleblizz.util.Catalog
import com.example.bubbleblizz.util.FavLine
import com.example.bubbleblizz.util.FavoritesStore

@Composable
fun ProductDetailsScreen(
    id: String,
    onBack: () -> Unit,
    onGoHome: () -> Unit,
    onGoCart: () -> Unit,
    onGoFavorites: () -> Unit,
    onGoSettings: () -> Unit
) {
    val p = remember(id) { Catalog.get(id) }
    var search by remember { mutableStateOf("") }
    var tab by remember { mutableStateOf(0) }

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
                        2 -> onGoFavorites()
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

            BackTopBar(
                title = p.name,
                onBack = onBack,
                trailing = { Text("LKR ${p.price}") }
            )

            Surface(
                tonalElevation = 1.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(Color(0xFFFFB74D), Color(0xFFFFECB3))
                                )
                            )
                    ) {
                        Image(
                            painter = painterResource(Images.ofName(p.drawableName)),
                            contentDescription = p.name,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        )
                    }

                    Spacer(Modifier.height(12.dp))
                    Text(p.description, textAlign = TextAlign.Left)
                }
            }

            Spacer(Modifier.height(12.dp))

            Row(Modifier.padding(horizontal = 16.dp)) {
                OutlinedButton(
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
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "ADD TO FAVORITE")
                }

                Spacer(Modifier.width(12.dp))

                Button(
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
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("ADD TO CART")
                }
            }

            Spacer(Modifier.height(80.dp))
        }
    }
}
