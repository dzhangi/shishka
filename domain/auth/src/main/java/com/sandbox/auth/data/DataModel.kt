package com.sandbox.auth.data

import com.sandbox.data.model.JokeDto

/**
 * Dumb data class for domain model
 */
data class DataModel(
    val value: String,
)

fun JokeDto.toDataModel() = DataModel(value)