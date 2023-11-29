package com.octocavern.data.model

data class AuthRequest(
    val username: String,
    val password: String,
    val type: String,
)
