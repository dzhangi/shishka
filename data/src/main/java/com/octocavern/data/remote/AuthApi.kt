package com.octocavern.data.remote

import com.octocavern.data.model.request.AuthRequest
import com.octocavern.data.model.response.UserAuthDetailsDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth")
    suspend fun login(@Body authRequest: AuthRequest): Response<UserAuthDetailsDto>
}