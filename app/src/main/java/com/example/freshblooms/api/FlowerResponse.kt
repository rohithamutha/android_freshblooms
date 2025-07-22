package com.example.freshblooms.api

data class FlowerResponse(
    val success: Boolean,
    val data: List<FlowerItem>
)

data class FlowerItem(
    val id: Int,
    val flowername: String,
    val category: String,
    val country: String,
    val image: String,
    val price: String,
    val stock_level: String,
    val offer: String,
    val delivary: String,
    val seasonal_flowers: String,
    val description: String,
    val short_description: String
)
