package com.example.freshblooms

data class PendingOrderResponse(
    val status: String,
    val orders: List<PendingOrder>
)

data class PendingOrder(
    val cid: String,
    val quantity: String,
    val status: String,
    val price: String,
    val flowername: String,
    val image: String,
    val firstname: String,
    val mobile: String,
    val total: String
)
