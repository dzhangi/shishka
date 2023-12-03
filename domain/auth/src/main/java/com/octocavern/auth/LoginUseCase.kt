package com.octocavern.auth

import com.octocavern.auth.model.UserAuthDetails
import com.octocavern.auth.model.toDomainModel
import com.octocavern.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {

    suspend operator fun invoke(
        login: String,
        password: String,
        type: String = "normal"
    ): UserAuthDetails {
        return authRepo.login(
            login = login,
            password = password,
            type = type
        ).toDomainModel()
    }
}