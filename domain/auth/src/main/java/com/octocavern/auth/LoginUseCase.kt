package com.octocavern.auth

import com.octocavern.auth.model.UserAuthDetails
import com.octocavern.auth.model.toDomainModel
import com.octocavern.auth.util.AuthError
import com.octocavern.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {

    suspend operator fun invoke(
        login: String,
        password: String,
        type: String = NORMAL,
    ): UserAuthDetails {
        if (login.isEmpty()) throw AuthError.EmptyLogin
        if (password.isEmpty()) throw AuthError.EmptyPassword

        return authRepo.login(
            login = login.trim(),
            password = password.trim(),
            type = type
        ).toDomainModel()
    }

    companion object LoginType {
        private const val NORMAL = "normal"
    }
}