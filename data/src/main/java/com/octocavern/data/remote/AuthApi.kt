package com.octocavern.data.remote

import com.octocavern.data.model.AuthRequest
import com.octocavern.data.model.UserAuthDetailsDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth")
    suspend fun login(@Body authRequest: AuthRequest): UserAuthDetailsDto
}