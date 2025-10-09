package com.example.bubbleblizz.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bubbleblizz.R
import com.example.bubbleblizz.ui.component.BubbleBottomBar

@Composable
fun HomeHeader(onCartClick: () -> Unit) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFFB06AB3), Color(0xFF4568DC))
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_bubbleblizz_logo_small),
                contentDescription = "App Logo",
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = "BubbleBlizz",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Explore Drinks",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f)
                )
            }
        }
        FilledIconButton(
            onClick = onCartClick,
            modifier = Modifier.size(48.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = Color.White.copy(alpha = 0.15f)
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Cart",
                tint = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMenu: (String) -> Unit,
    onOrders: () -> Unit,
    onProfile: () -> Unit,
    onFavorites: () -> Unit,
    onCart: () -> Unit,
    onBack: () -> Unit,
    onDetails: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
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
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            HomeHeader(onCartClick = onCart)
            SearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = { active = false },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Search drinks...") },
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                colors = SearchBarDefaults.colors(
                    containerColor = Color(0xFFF4F4F4),
                    dividerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {}
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
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
                HeroSection(
                    title = "FIND YOUR BLIZZ",
                    onClick = { onMenu("Fruit Juice") }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CategoryGrid(
                    onFruit = { onMenu("Fruit Juice") },
                    onDairy = { onMenu("Dairy Drinks") },
                    onSoft = { onMenu("Soft Drink") },
                    onEnergy = { onMenu("Energy Drink") }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Most Popular",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {
                    PopularCard(
                        id = "pineapple",
                        name = "Pineapple Juice",
                        imageRes = R.drawable.img_pineapple,
                        onClick = { productId ->
                            onDetails(productId)
                        }
                    )

                    PopularCard(
                        id = "choco_milk",
                        name = "Chocolate Milk",
                        imageRes = R.drawable.img_chocomilk,
                        onClick = { productId ->
                            onDetails(productId)
                        }
                    )

                    PopularCard(
                        id = "fanta",
                        name = "Fanta Sugarfree",
                        imageRes = R.drawable.img_fanta,
                        onClick = { productId ->
                            onDetails(productId)
                        }
                    )

                    PopularCard(
                        id = "monster",
                        name = "Monster Energy",
                        imageRes = R.drawable.img_monster,
                        onClick = { productId ->
                            onDetails(productId)
                        }
                    )
                }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }


@Composable
private fun PopularCard(
    id: String,
    name: String,
    imageRes: Int,
    onClick: (String) -> Unit
) {
    val gradient = Brush.verticalGradient(
        listOf(Color(0xFFE0C3FC), Color(0xFF8EC5FC))
    )
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(horizontal = 10.dp)
            .shadow(8.dp, RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .background(gradient)
            .clickable { onClick(id) }
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF222222)
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Rs. 450.00",
            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF4A4A4A)),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
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
        verticalArrangement = Arrangement.spacedBy(20.dp)
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
                    .aspectRatio(0.95f)
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
