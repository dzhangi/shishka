package com.octocavern.data.model.response

import com.google.gson.annotations.SerializedName

data class UserAuthDetailsDto(
    @SerializedName("accepted_terms")
    val acceptedTerms: Boolean,
    @SerializedName("auth_token")
    val authToken: String,
    @SerializedName("big_photo")
    val bigPhoto: Any,
    val bio: String,
    val color: String,
    @SerializedName("date_joined")
    val dateJoined: String,
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("full_name_display")
    val fullNameDisplay: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    val lang: String,
    @SerializedName("max_memberships_private_projects")
    val maxMembershipsPrivateProjects: Any,
    @SerializedName("max_memberships_public_projects")
    val maxMembershipsPublicProjects: Any,
    @SerializedName("max_private_projects")
    val maxPrivateProjects: Any,
    @SerializedName("max_public_projects")
    val maxPublicProjects: Any,
    val photo: Any,
    @SerializedName("read_new_terms")
    val readNewTerms: Boolean,
    val refresh: String,
    val roles: List<String>,
    val theme: String,
    val timezone: String,
    @SerializedName("total_private_projects")
    val totalPrivateProjects: Int,
    @SerializedName("total_public_projects")
    val totalPublicProjects: Int,
    val username: String,
    val uuid: String
)