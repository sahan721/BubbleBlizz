package com.example.bubbleblizz.util

import androidx.compose.runtime.mutableStateListOf

data class FavLine(
    val id: String,
    val name: String,
    val size: String,
    val price: Int,
    val rating: Double,
    val drawableName: String
)

object FavoritesStore {
    val items = mutableStateListOf<FavLine>()

    fun toggle(item: FavLine) {
        val existing = items.find { it.id == item.id }
        if (existing != null) {
            items.remove(existing)
        } else {
            items.add(item)
        }
    }

    fun remove(id: String) {
        items.removeAll { it.id == id }
    }

    fun contains(id: String): Boolean {
        return items.any { it.id == id }
    }

    fun clear() {
        items.clear()
    }
}
