package com.example.bubbleblizz.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bubbleblizz.navigation.AppNav
import com.example.bubbleblizz.ui.ThemeViewModel
import com.example.bubbleblizz.ui.ThemeMode
import com.example.bubbleblizz.ui.theme.BubbleBlizzTheme

@Composable
fun App() {
    val themeVm: ThemeViewModel = viewModel()
    val mode by themeVm.mode.collectAsState()  // ✅ observes state changes

    BubbleBlizzTheme(darkTheme = (mode == ThemeMode.DARK)) {  // ✅ reacts live
        AppNav(themeVm = themeVm)
    }
}
