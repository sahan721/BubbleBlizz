package com.example.bubbleblizz.ui.component
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bubbleblizz.R

@Composable
fun GradientHeader(
    search: String,
    onSearch: (String) -> Unit
) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFFB06AB3), Color(0xFF4568DC))
    )

    Column(
        Modifier
            .fillMaxWidth()
            .background(
                gradient,
                RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(top = 12.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(R.drawable.ic_bubbleblizz_logo_small),
                contentDescription = "Bubble Blizz",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.width(12.dp))

            OutlinedTextField(
                value = search,
                onValueChange = onSearch,
                singleLine = true,
                placeholder = { Text("Find") },
                trailingIcon = {
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                shape = RoundedCornerShape(22.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.18f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.12f),
                    focusedBorderColor = Color.White.copy(alpha = 0.6f),
                    unfocusedBorderColor = Color.White.copy(alpha = 0.4f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White
                )
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopBar(
    title: String,
    onBack: () -> Unit,
    trailing: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(title, color = MaterialTheme.colorScheme.onBackground) },
        navigationIcon = {
            // Back Button
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground // ensures visibility in dark/light modes
                )
            }
        },
        actions = {
            trailing?.invoke()
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent, // transparent background to match your gradient theme
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
@Composable
fun BubbleBottomBar(
    selected: Int,
    onSelect: (Int) -> Unit
) {
    val items = listOf(
        Pair(Icons.Default.Home, "Home"),
        Pair(Icons.Default.ShoppingCart, "Cart"),
        Pair(Icons.Default.Star, "Favourites"),
        Pair(Icons.Default.Settings, "Settings")
    )

    // Gradient background
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFFB06AB3), Color(0xFF4568DC))
                )
            )
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selected == index

                Column(
                    modifier = Modifier
                        .weight(1f) // ✅ ensures all are equal width
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (isSelected) Color(0xFFFFEB3B) else Color.White
                        )
                        .clickable { onSelect(index) }
                        .padding(vertical = 8.dp, horizontal = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = item.first,
                        contentDescription = item.second,
                        tint = if (isSelected) Color.Black else Color.Black,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = item.second,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomItem(
    icon: ImageVector,
    label: String,
    active: Boolean,
    onClick: () -> Unit
) {
    val pill = if (active) Color(0xFFFFEB3B) else Color.White

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(pill.copy(alpha = if (active) 1f else 0.9f))
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(26.dp)
        )

        Spacer(Modifier.height(2.dp))

        Text(
            label,
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = if (active) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val gradient = Brush.horizontalGradient(
        listOf(Color(0xFFB06AB3), Color(0xFF4568DC))
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient, RoundedCornerShape(50))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
