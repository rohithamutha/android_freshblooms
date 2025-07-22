package com.example.freshblooms

data class AdminOrder(
    val cid: String,
    val flowername: String,
    val quantity: String,
    val status: String,
    val price: String,
    val image: String,
    val firstname: String,
    val mobile: String,
    val total: Double
)
