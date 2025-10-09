package com.example.bubbleblizz.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Mode Colors
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFC107),
    secondary = Color(0xFF03A9F4),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFDFDFD),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Dark Mode Colors
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFF9800),
    secondary = Color(0xFF80D8FF),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

// Theme Composable
@Composable
fun BubbleBlizzTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
