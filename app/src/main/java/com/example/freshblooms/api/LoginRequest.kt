package com.example.freshblooms.api

data class LoginRequest(
    val email: String,
    val password: String
)
data class LoginResponse(
    val status: Boolean,
    val message: String,
    val id: Int? = null,
    val name: String? = null,
    val usertype: String? = null,
    val phone: String? = null,
    val email: String? = null
)