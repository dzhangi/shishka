package com.octocavern.data.remote

import com.octocavern.data.model.JokeDto
import retrofit2.http.GET

interface JokeApi {
    @GET("jokes/random")
    suspend fun fetchJoke(): JokeDto
}