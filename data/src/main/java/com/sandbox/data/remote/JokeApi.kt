package com.sandbox.data.remote

import com.sandbox.data.model.JokeDto
import retrofit2.http.GET

interface JokeApi {
    @GET("jokes/random")
    suspend fun fetchJoke(): JokeDto
}