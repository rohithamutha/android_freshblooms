package com.example.freshblooms

data class AdminOrderResponse(
    val status: String,
    val orders: List<AdminOrder>
)
