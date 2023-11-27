package com.sandbox.data.repository

import com.sandbox.data.remote.JokeApi
import javax.inject.Inject

class ChuckRepository @Inject constructor(private val jokeApi: JokeApi) {
    suspend fun fetchJoke() = jokeApi.fetchJoke()
}