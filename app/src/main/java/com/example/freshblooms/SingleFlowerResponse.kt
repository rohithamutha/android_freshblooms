package com.example.freshblooms

data class SingleFlowerResponse(
    val success: Boolean,
    val data: FlowerData
)

data class FlowerData(
    val id: Int,
    val flowername: String,
    val image: String,
    val price: String,
    val seasonal_flowers: String,
    val description: String
)
