package com.octocavern.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("big_photo")
    val bigPhoto: Any,
    @SerializedName("full_name_display")
    val fullNameDisplay: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("photo")
    val photo: Any,
    @SerializedName("username")
    val username: String
)