package com.example.freshblooms

data class DesignResponse(
    val success: Boolean,
    val data: List<DesignItem>
)

data class DesignItem(
    val id: Int,
    val flowername: String,
    val category: String,
    val country: String,
    val image: String,
    val price: String,
    val stock_level: String?,
    val offer: String,
    val delivary: String,
    val seasonal_flowers: String,
    val description: String,
    val short_description: String
)
