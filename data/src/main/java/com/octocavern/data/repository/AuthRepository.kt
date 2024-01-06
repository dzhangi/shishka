package com.octocavern.data.repository

import com.octocavern.data.local.ShishkaPrefs
import com.octocavern.data.model.request.AuthRequest
import com.octocavern.data.model.response.UserAuthDetailsDto
import com.octocavern.data.remote.TaigaApi
import com.octocavern.data.util.extractErrorMessage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val api: TaigaApi,
    private val prefs: ShishkaPrefs,
) {

    suspend fun signIn(
        login: String,
        password: String,
        type: String,
    ): UserAuthDetailsDto {
        val response = api.login(
            AuthRequest(
                username = login,
                password = password,
                type = type
            )
        )

        val body = response.body()!!

        if (response.isSuccessful) {
            prefs.saveUserId(body.id)
        } else {
            throw Exception(response.extractErrorMessage("detail"))
        }

        return body
    }
}