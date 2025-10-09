package com.example.bubbleblizz.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
val AppTypography = Typography(
    titleLarge = TextStyle(fontFamily = FontFamily.Default, fontSize = 22.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontFamily = FontFamily.Default, fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontFamily = FontFamily.Default, fontSize = 16.sp),
    labelLarge = TextStyle(fontFamily = FontFamily.Default, fontSize = 14.sp, fontWeight = FontWeight.Medium),
)