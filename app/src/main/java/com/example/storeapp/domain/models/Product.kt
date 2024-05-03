package com.example.storeapp.domain.models

data class Product(
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
){
    val computedPrice get() = "$$price"
    val computedTitle get() = if (title.length > 11) title.substring(0, 20) else title
}