package com.example.bubbleblizz.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class ThemeMode { LIGHT, DARK }

class ThemeViewModel : ViewModel() {

    private val _mode = MutableStateFlow(ThemeMode.LIGHT)
    val mode: StateFlow<ThemeMode> = _mode

    fun toggleDark(enabled: Boolean) {
        _mode.value = if (enabled) ThemeMode.DARK else ThemeMode.LIGHT
    }


    fun isDark() = _mode.value == ThemeMode.DARK
}
