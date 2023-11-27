package com.octocavern.auth.data

import com.octocavern.data.model.JokeDto

/**
 * Dumb data class for domain model
 */
data class DataModel(
    val value: String,
)

fun JokeDto.toDataModel() = DataModel(value)