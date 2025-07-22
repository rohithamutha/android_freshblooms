package com.example.freshblooms

data class TransactionHistoryResponse(
    val status: String,
    val transactions: List<Transaction>
)

data class Transaction(
    val payment_id: String,
    val created_at: String,
    val total_amount: String,
    val formatted_date: String
)
