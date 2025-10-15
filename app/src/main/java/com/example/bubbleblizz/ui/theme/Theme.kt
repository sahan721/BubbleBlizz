package com.example.bubbleblizz.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



// Light Mode Colors
private val LightColorScheme = lightColorScheme(
    primary = BrandPrimary,      // Gold beige
    secondary = BrandSecondary,  // Brown accent
    tertiary = BrandTertiary,    // Cream background
    background = BrandTertiary,  // General background
    surface = SurfaceLight,      // Light surface color
    onPrimary = Color.White,     // Text/icon color on gold
    onSecondary = Color.White,   // Text/icon color on brown
    onTertiary = Color.Black,    // Text on cream
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Dark Mode Colors
private val DarkColorScheme = darkColorScheme(
    primary = BrandSecondary,    // Brown for dark mode
    secondary = BrandPrimary,    // Gold accent
    tertiary = BrandTertiary,    // Optional cream accent
    background = SurfaceDark,
    surface = SurfaceDark,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
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
