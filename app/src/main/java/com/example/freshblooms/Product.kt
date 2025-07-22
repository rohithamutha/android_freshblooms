package com.example.freshblooms

data class Product(
    val name: String,
    val category: String,
    val imageRes: Int, // You can use image URL if using Glide
    val price: Int,
    val stock: Int,
    val offer: Int,
    val delivery: String,
    val season: String
)
