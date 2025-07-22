package com.example.freshblooms.api

data class SignupRequest(
    val name: String,
    val email: String,
    val Password: String,
    val phone_no: String
)

data class SignupResponse(
    val status: Boolean,
    val message: String
)