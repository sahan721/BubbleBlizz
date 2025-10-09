package com.example.bubbleblizz.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bubbleblizz.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(onDone: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(if (visible) 1f else 0f, label = "alpha")
    val scale by animateFloatAsState(if (visible) 1f else 0.9f, label = "scale")

    LaunchedEffect(Unit) {
        visible = true
        delay(1300)
        onDone()
    }

    val gradient = Brush.verticalGradient(
        listOf(Color(0xFFB06AB3), Color(0xFF4568DC))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center   // Center the content inside the box
    ) {
        Image(
            painter = painterResource(R.drawable.ic_bubbleblizz_logo),
            contentDescription = "Bubble Blizz",
            modifier = Modifier
                .size(160.dp)
                .alpha(alpha)
                .scale(scale)
        )
    }
}
