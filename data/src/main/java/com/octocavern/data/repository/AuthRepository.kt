package com.octocavern.data.repository

import com.octocavern.data.model.AuthRequest
import com.octocavern.data.model.UserAuthDetailsDto
import com.octocavern.data.remote.TaigaApi
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: TaigaApi) {
    suspend fun login(
        login: String,
        password: String,
        type: String,
    ): UserAuthDetailsDto {
        return api.login(
            AuthRequest(
                username = login,
                password = password,
                type = type
            )
        )
    }
}