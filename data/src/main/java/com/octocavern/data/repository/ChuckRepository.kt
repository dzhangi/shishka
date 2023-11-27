package com.octocavern.data.repository

import com.octocavern.data.remote.JokeApi
import javax.inject.Inject

class ChuckRepository @Inject constructor(private val jokeApi: JokeApi) {
    suspend fun fetchJoke() = jokeApi.fetchJoke()
}