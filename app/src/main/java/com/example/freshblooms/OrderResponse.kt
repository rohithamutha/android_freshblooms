package com.example.freshblooms

import Order

data class OrderResponse(
    val status: String,
    val orders: List<Order>
)
