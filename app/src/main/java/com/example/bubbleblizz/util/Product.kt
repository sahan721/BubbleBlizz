package com.example.bubbleblizz.util


data class Product(
    val id: String,
    val name: String,
    val size: String,
    val price: Int,
    val rating: Double = 4.4,
    val description: String,
    val drawableName: String,
    val category: String
)
