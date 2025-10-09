package com.example.bubbleblizz.ui.screen

import android.R.attr.delay
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bubbleblizz.R
import com.example.bubbleblizz.ui.component.BubbleBottomBar
import com.example.bubbleblizz.ui.component.GradientHeader
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    onMenu: (String) -> Unit,
    onOrders: () -> Unit,
    onProfile: () -> Unit,
    onFavorites: () -> Unit,
    onCart: () -> Unit,
    onBack: () -> Unit
) {
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
                        0 -> Unit
                        1 -> onCart()
                        2 -> onFavorites()
                        3 -> onProfile()
                    }
                }
            )
        }
    ) { padding ->

        // ✅ Scrollable Content
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // --- Welcome Row ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier
                        .size(22.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Welcome Back 👋",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- Hero Banner ---
            HeroSection(
                title = "FIND YOUR BLIZZ",
                onClick = { onMenu("Fruit Juice") }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // --- Category Grid ---
            CategoryGrid(
                onFruit = { onMenu("Fruit Juice") },
                onDairy = { onMenu("Dairy Drinks") },
                onSoft = { onMenu("Soft Drink") },
                onEnergy = { onMenu("Energy Drink") }
            )

            Spacer(modifier = Modifier.height(90.dp)) // Prevent overlap with bottom nav
        }
    }
}


@Composable
private fun HeroSection(title: String, onClick: () -> Unit) {
    Box(
        Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_banner),
            contentDescription = "Hero Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
        ) {
            Text(
                title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(Modifier.height(12.dp))
            ElevatedButton(
                onClick = onClick,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White)
            ) {
                Text("SHOP NOW", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CategoryCard(
    title: String,
    gradient: Brush,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(brush = gradient)
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 8.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
private fun CategoryGrid(
    onFruit: () -> Unit,
    onDairy: () -> Unit,
    onSoft: () -> Unit,
    onEnergy: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp) // ⬅ smaller vertical gap
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CategoryCard(
                title = "Fruit Juice",
                gradient = Brush.verticalGradient(listOf(Color(0xFFFFE8E8), Color(0xFFFFB6B9))),
                imageRes = R.drawable.img_fruitjuice,
                onClick = onFruit,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(0.95f) // ⬅ slightly shorter
            )
            CategoryCard(
                title = "Dairy Drinks",
                gradient = Brush.verticalGradient(listOf(Color(0xFFE8F4FF), Color(0xFFAEDFF7))),
                imageRes = R.drawable.img_dairydrink,
                onClick = onDairy,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(0.95f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CategoryCard(
                title = "Soft Drink",
                gradient = Brush.verticalGradient(listOf(Color(0xFFFFF5D1), Color(0xFFFFD194))),
                imageRes = R.drawable.img_softdrink,
                onClick = onSoft,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(0.95f)
            )
            CategoryCard(
                title = "Energy Drink",
                gradient = Brush.verticalGradient(listOf(Color(0xFFE8FFE5), Color(0xFFA8E063))),
                imageRes = R.drawable.img_energydrink,
                onClick = onEnergy,
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(0.95f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FancyCategoryCard(
    title: String,
    gradient: Brush,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (pressed) 0.97f else 1f, label = "scale")

    Card(
        onClick = {
            pressed = true
            onClick()
            pressed = false
        },
        modifier = modifier
            .aspectRatio(0.9f)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = gradient,
                    alpha = 0.65f // softer background gradient
                )
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image container with shadow
                Box(
                    modifier = Modifier
                        .size(75.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.8f))
                        .padding(6.dp)
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = title,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = title,
                    color = Color(0xFF101010),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
