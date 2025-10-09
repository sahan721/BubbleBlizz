package com.example.bubbleblizz.util

import androidx.compose.runtime.mutableStateListOf

data class CartLine(
    val id: String,
    val name: String,
    val sub: String,
    val price: Int,
    var qty: Int = 1
)

object CartStore {
    val lines = mutableStateListOf<CartLine>()

    fun add(item: CartLine) {
        val i = lines.indexOfFirst { it.id == item.id }
        if (i >= 0)
            lines[i] = lines[i].copy(qty = lines[i].qty + item.qty)
        else
            lines += item
    }

    fun updateQty(id: String, qty: Int) {
        val i = lines.indexOfFirst { it.id == id }
        if (i >= 0)
            lines[i] = lines[i].copy(qty = maxOf(1, qty))
    }

    fun remove(id: String) {
        lines.removeAll { it.id == id }
    }

    fun total(): Int = lines.sumOf { it.price * it.qty }

    fun clear() {
        lines.clear()
    }
}
