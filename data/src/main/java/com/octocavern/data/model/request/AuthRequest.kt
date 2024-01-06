package com.octocavern.data.model.request

data class AuthRequest(
    val username: String,
    val password: String,
    val type: String,
)
