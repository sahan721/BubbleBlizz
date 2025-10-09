package com.example.bubbleblizz.util
import androidx.compose.runtime.mutableStateListOf
data class CartLine(val id: String, val name: String, val sub: String, val price: Int, var qty: Int = 1)
data class FavLine(val id: String, val name: String, val sub: String, val price: Int, val rating: Double = 4.4)
object CartStore {
    val lines = mutableStateListOf<CartLine>()
    fun add(item: CartLine) { val i = lines.indexOfFirst { it.id == item.id }; if (i >= 0) lines[i] = lines[i].copy(qty = lines[i].qty + item.qty) else lines += item }
    fun updateQty(id: String, qty: Int) { val i = lines.indexOfFirst { it.id == id }; if (i >= 0) lines[i] = lines[i].copy(qty = maxOf(1, qty)) }
    fun remove(id: String) { lines.removeAll { it.id == id } }
    fun total(): Int = lines.sumOf { it.price * it.qty }
    fun clear() { lines.clear() }
}
object FavoritesStore {
    val items = mutableStateListOf<FavLine>()
    fun toggle(item: FavLine) { if (contains(item.id)) remove(item.id) else add(item) }
    fun add(item: FavLine) { if (!contains(item.id)) items += item }
    fun remove(id: String) { items.removeAll { it.id == id } }
    fun contains(id: String) = items.any { it.id == id }
}