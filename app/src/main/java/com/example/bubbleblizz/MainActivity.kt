package com.example.bubbleblizz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bubbleblizz.util.App
import com.example.bubbleblizz.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // ✅ Apply your normal theme AFTER splash background is shown
        setTheme(R.style.Theme_BubbleBlizz)

        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}
