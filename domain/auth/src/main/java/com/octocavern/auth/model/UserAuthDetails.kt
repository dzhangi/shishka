package com.octocavern.auth.model

import com.octocavern.data.model.response.UserAuthDetailsDto

data class UserAuthDetails(
    val id: Int,
    val fullName: String,
    val email: String,
)

fun UserAuthDetailsDto.toDomainModel() = UserAuthDetails(
    id = id,
    fullName = fullName,
    email = email,
)
