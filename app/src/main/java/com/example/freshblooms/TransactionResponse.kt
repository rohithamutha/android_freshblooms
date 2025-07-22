package com.example.freshblooms

data class TransactionResponse(
    val status: String,
    val transactions: List<Transaction>
)
