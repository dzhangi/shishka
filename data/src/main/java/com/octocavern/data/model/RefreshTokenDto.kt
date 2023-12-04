package com.octocavern.data.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenDto(
    @SerializedName("auth_token")
    val authToken: String,
    val refresh: String
)
