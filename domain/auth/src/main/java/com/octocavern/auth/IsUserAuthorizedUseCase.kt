package com.octocavern.auth

import com.octocavern.data.repository.ProfileRepository
import javax.inject.Inject

class IsUserAuthorizedUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    operator fun invoke(): Boolean {
        return profileRepository.isAuthorized()
    }
}